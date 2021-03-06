package co.codingnomads.kraken.model.trade.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Meghan Boyce on 12/07/17
 *
 */

public class KrakenCancelOpenOrder {

    // Number of orders canceled
    int count;
    // If set, order(s) is/are pending cancellation
    boolean pending;

    public KrakenCancelOpenOrder(@JsonProperty("count")int count,
                                 @JsonProperty("pending")boolean pending) {
        this.count = count;
        this.pending = pending;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public boolean isPending() {
        return pending;
    }

    public void setPending(boolean pending) {
        this.pending = pending;
    }

}
