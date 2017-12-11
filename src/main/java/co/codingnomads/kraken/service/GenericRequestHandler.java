package co.codingnomads.kraken.service;

import co.codingnomads.kraken.exception.RateLimitException;
import co.codingnomads.kraken.exception.UnkownException;
import co.codingnomads.kraken.model.*;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Service
public class GenericRequestHandler {

    public OutputWrapper callAPI(KrakenRequestEnum krakenRequest,
                                 RequestBodyGeneric requestBody,
                                 ApiAuthentication apiAuthentication,
                                 String ... queryParams)
            throws NullPointerException, UnkownException, RateLimitException {


        MultiValueMap<String, String> body = null;
        HttpHeaders headers = null;

        //no need of doing it for public method
        if (krakenRequest.getHttpMethod().matches("POST")) {
            try {
                if (!CallCounter.isUnderRateLimit(apiAuthentication, krakenRequest)) {
                    throw new UnkownException("RateLimit Exception - callApi +");
                    //todo Kevin: aren't we missing something after the +
                }
            } catch (RateLimitException e) {
                e.printStackTrace();
                throw e;
            }

            body = requestBody.postParam();
            headers = getHttpPostHeaders(krakenRequest, requestBody, apiAuthentication);
        }

        System.out.println("callAPI executing - " + Thread.currentThread().getName());
        //todo Kevin: needed?

        // Call a method to set the fullURL with any arguments that have been passed in, pass it queryParams
        // if queryPArams>0, format correctly

        //the entity with the body and the headers
        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(body, headers);

        // need an Autowired version of it but I am getting a null pointer issue
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity response = restTemplate.exchange(
                krakenRequest.getFullURL(),
                krakenRequest.getHttpMethod(),
                entity,
                krakenRequest.getOutputClass());

        // can make a method to check this outside this method
        try {
            if (isSuccessful(response.getStatusCode())) {
                return (OutputWrapper) response.getBody();
            } else throw new RestClientException(response.getStatusCode().getReasonPhrase());
        } catch (RestClientException e) {
            throw e;
        }
    }


    public HttpHeaders getHttpPostHeaders(KrakenRequestEnum krakenRequest,
                                          RequestBodyGeneric requestBody,
                                          ApiAuthentication apiAuthentication) {

        HttpHeaders headers = new HttpHeaders();
        headers.set("API-Key", apiAuthentication.getApiKey());
        headers.set("API-Sign", KrakenSignature.ApiSignCreator(requestBody.getNonce(),
                    requestBody.signPostParam(), apiAuthentication.getSecret(), krakenRequest.getEndPoint()));

        return headers;
    }

    // need to go somewhere else
    public boolean isSuccessful(HttpStatus status)
            throws RestClientException {
        if (status.is2xxSuccessful())
            return true;
        else if (status.is4xxClientError())
            throw new HttpClientErrorException(status);
        else if (status.is5xxServerError())
            throw new HttpServerErrorException(status);
        else
            throw new RestClientException(status.getReasonPhrase());
    }
}