package co.codingnomads.kraken.model;

import co.codingnomads.kraken.model.account.response.GetTradeVolumeOutput;
import co.codingnomads.kraken.model.account.response.*;
import co.codingnomads.kraken.model.market.response.*;
import co.codingnomads.kraken.model.trade.response.*;

import org.springframework.http.HttpMethod;

/**
 * Created by Thomas Leruth on 11/29/17
 */

/**
 * Enum class to get specific information about the calls as
 * 1) endPoint
 * 2) Type of HTTP call
 * 3) Number of call allowance used to make that call (for the call limiter)
 * 4) Class of the response output
 */
public enum KrakenRequestEnum {

    GETSERVERTIME("/0/public/Time", HttpMethod.GET, 0, GetServerTimeOutput.class),
    GETASSETINFO("/0/public/Assets", HttpMethod.GET, 0, GetAssetInfoOutput.class),
    GETTRADABLEASSETPAIRS("/0/public/AssetPairs", HttpMethod.GET, 0, GetTradableAssetPairsOutput.class),
    GETTICKERINFORMATION("/0/public/Ticker", HttpMethod.GET, 0, GetTickerInformationOutput.class),
    GETOHLCDATA("/0/public/OHLC", HttpMethod.GET, 0, GetOHLCOutput.class),
    GETORDERBOOK("/0/public/Depth", HttpMethod.GET, 0, GetOrderBookOutput.class),
    GETRECENTTRADES("/0/public/Trades", HttpMethod.GET, 0, GetRecentTradesOutput.class),
    GETRECENTSPREADDATA("/0/public/Spread", HttpMethod.GET, 0, GetSpreadDataOutput.class),
    GETACCOUNTBALANCE("/0/private/Balance", HttpMethod.POST, 1, GetAccountBalanceOutput.class),
    GETTRADEBALANCE("/0/private/TradeBalance", HttpMethod.POST, 1, GetTradeBalanceOutput.class),
    GETOPENORDERS("/0/private/OpenOrders", HttpMethod.POST, 1, GetOpenOrdersOutput.class),
    GETCLOSEDORDERS("/0/private/ClosedOrders", HttpMethod.POST, 1, GetClosedOrdersOutput.class),
    QUERYORDERINFO("/0/private/QueryOrders", HttpMethod.POST, 1, QueryOrderInfoOutput.class),
    GETTRADESHISTORY("/0/private/TradesHistory", HttpMethod.POST, 2, GetTradesHistoryOutput.class),
    QUERYTRADESINFO("/0/private/QueryTrades", HttpMethod.POST, 1, QueryTradesInfoOutput.class),
    GETOPENPOSITIONS("/0/private/OpenPositions",HttpMethod.POST, 1,GetOpenPositionsOutput.class),
   GETLEDGERSINFO("/0/private/Ledgers", HttpMethod.POST, 2, GetLedgersInfoOutput.class),
    QUERYLEDGERS("/0/private/QueryLedgers", HttpMethod.POST, 2, QueryLedgersOutput.class),
    GETTRADEVOLUME("/0/private/TradeVolume", HttpMethod.POST, 1, GetTradeVolumeOutput.class),
    ADDSTRANDARDORDERS("/0/private/AddOrder", HttpMethod.POST, 0, AddStandardOrderOutput.class),
    CANCELOPENORDERS("/0/private/CancelOrder",HttpMethod.POST, 0,CancelOpenOrderOutput.class);

    private String endPoint;
    private final HttpMethod httpMethod;
    private final Class outputClass;
    private final int callAmount;
    private String fullURL;
    private final String domain = "https://api.kraken.com";

    public String getEndPoint() {
        return endPoint;
    }

    public HttpMethod getHttpMethod() {
        return httpMethod;
    }

    public String getDomain() {
        return domain;
    }

    public String getFullURL() {
        return fullURL;
    }

    public Class getOutputClass() {
        return outputClass;
    }

    public void updateEndpoint(String queryParams){
        this.endPoint = this.endPoint + queryParams;
        this.fullURL = this.fullURL + queryParams;
    }

    KrakenRequestEnum(String endPoint, HttpMethod httpMethod, int callAmount, Class outputClass) {
        this.endPoint = endPoint;
        this.httpMethod = httpMethod;
        this.callAmount = callAmount;
        this.outputClass = outputClass;
        this.fullURL = domain + endPoint;
    }

    KrakenRequestEnum(String endPoint, HttpMethod httpMethod, int callAmount, Class outputClass, String pair) {
        this.endPoint = endPoint + pair;
        this.httpMethod = httpMethod;
        this.callAmount = callAmount;
        this.outputClass = outputClass;
        this.fullURL = domain + endPoint + pair;
    }

    public int getCallAmount() {
        return callAmount;
    }

}


