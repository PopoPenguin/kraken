package co.codingnomads.kraken.model.account.request;

import co.codingnomads.kraken.model.RequestBodyGeneric;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

public class GetClosedOrdersRequestBody extends RequestBodyGeneric {
    /**
     * Created by Meghan Boyce on 11/29/17
     *
     * All instance vars defined as Strings until we know better
     */

    // Whether or not to include trades in output (optional - default = false)
    String trades;
    // Restrict results to given user reference id (optional)
    String userref;
    // Starting unix timestamp or GetOpenOrdersOutput tx id of results (optional, exclusive)
    String start;
    // Ending unix timestamp or GetOpenOrdersOutput tx id of results (optional, inclusive)
    String end;
    // Result offset
    String ofs;
    // Which GetOpenOrdersOutput to use (optional - open, close, both (default))
    String closetime;

    // Fully qualified Constructor
    public GetClosedOrdersRequestBody(String trades, String userref, String start, String end,
                                      String ofs, String closetime) {
        this.trades = trades;
        this.userref = userref;
        this.start = start;
        this.end = end;
        this.ofs = ofs;
        this.closetime = closetime;
    }

    public String getTrades() {
        return trades;
    }

    public void setTrades(String trades) {
        this.trades = trades;
    }

    public String getUserref() {
        return userref;
    }

    public void setUserref(String userref) {
        this.userref = userref;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public String getOfs() {
        return ofs;
    }

    public void setOfs(String ofs) {
        this.ofs = ofs;
    }

    public String getClosetime() {
        return closetime;
    }

    public void setClosetime(String closetime) {
        this.closetime = closetime;
    }

    @Override
    public String toString() {
        return "GetClosedOrdersRequestBody{" +
                "trades='" + trades + '\'' +
                ", userref='" + userref + '\'' +
                ", start='" + start + '\'' +
                ", end='" + end + '\'' +
                ", ofs='" + ofs + '\'' +
                ", closetime='" + closetime + '\'' +
                '}';
    }

    @Override
    public MultiValueMap<String, String> postParam(){
        MultiValueMap<String, String> postParameters = new LinkedMultiValueMap<String, String>();
        postParameters.add("nonce", super.getNonce());
        postParameters.add("trades", getTrades());
        postParameters.add("userref", getUserref());
        postParameters.add("start", getStart());
        postParameters.add("end", getEnd());
        postParameters.add("ofs", getOfs());
        postParameters.add("closetime", getClosetime());
        return postParameters;
    }

    @Override
    public String signPostParam() {
        StringBuilder sb = new StringBuilder();
        sb.append("nonce").append("=").append(getNonce());
        sb.append("&").append("trades").append("=").append(getTrades());
        sb.append("&").append("userref").append("=").append(getUserref());
        sb.append("&").append("start").append("=").append(getStart());
        sb.append("&").append("end").append("=").append(getEnd());
        sb.append("&").append("ofs").append("=").append(getOfs());
        sb.append("&").append("closetime").append("=").append(getClosetime());
        return sb.toString();
    }
}
