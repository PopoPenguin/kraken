package co.codingnomads.kraken.model.account.request;

import co.codingnomads.kraken.model.RequestBodyGeneric;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

public class GetOpenOrdersRequestBody extends RequestBodyGeneric {

    // Whether or not to include trades in output (optional, default = false)
    Boolean trades;
    // Restrict results to given user reference id (optional)
    String userref;

    // Fully qualified constructor
    public GetOpenOrdersRequestBody(Boolean trades, String userref) {
        this.trades = trades;
        this.userref = userref;
    }

    // getters and setters
    public Boolean getTrades() {
        return trades;
    }

    public void setTrades(Boolean trades) {
        this.trades = trades;
    }

    public String getUserref() {
        return userref;
    }

    public void setUserref(String userref) {
        this.userref = userref;
    }

    @Override
    public String toString() {
        return "GetOpenOrdersRequestBody{" +
                "trades=" + trades +
                ", userref='" + userref + '\'' +
                ", nonce='" + nonce + '\'' +
                '}';
    }

    @Override
    public MultiValueMap<String, String> postParam(){
        MultiValueMap<String, String> postParameters = new LinkedMultiValueMap<>();
        postParameters.add("nonce", getNonce());
        postParameters.add("trades", getTrades().toString());
        postParameters.add("userref", getUserref());
        return postParameters;
    }

    @Override
    public String signPostParam() {
        StringBuilder sb = new StringBuilder();
        sb.append("nonce").append("=").append(getNonce());
        sb.append("&").append("trades").append("=").append(getTrades().toString());
        sb.append("&").append("userref").append("=").append(getUserref());
        return sb.toString();
    }
}