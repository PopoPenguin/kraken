package co.codingnomads.kraken.model.cancelOrder;

import co.codingnomads.kraken.model.RequestBodyGeneric;

public class CancelOpenOrderRequestBody extends RequestBodyGeneric {

    // Transaction id
    String txid;

    public CancelOpenOrderRequestBody(String txid) {
        this.txid = txid;
    }

    public String getTxid() {
        return txid;
    }

    public void setTxid(String txid) {
        this.txid = txid;
    }
}
