package co.codingnomads.kraken.model.market.response.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Thomas Leruth on 11/29/17
 */

public class KrakenServerTime {

    // as unix timestamp
    String unixtime;
    // as RFC 1123 time format
    String rfc1123;

//    public String getUnixtime() {
//        return unixtime;
//    }
//
//    public void setUnixtime(String unixtime) {
//        this.unixtime = unixtime;
//    }
//
//    public String getRfc1123() {
//        return rfc1123;
//    }
//
//    public void setRfc1123(String rfc1123) {
//        this.rfc1123 = rfc1123;
//    }

    public KrakenServerTime(@JsonProperty("unixtime") String unixtime,
                            @JsonProperty("rfc1123") String rfc1123) {
        this.unixtime = unixtime;
        this.rfc1123 = rfc1123;
    }

    @Override
    public String toString() {
        return "KrakenServerTime{" +
                "unixtime='" + unixtime + '\'' +
                ", rfc1123='" + rfc1123 + '\'' +
                '}';
    }

}
