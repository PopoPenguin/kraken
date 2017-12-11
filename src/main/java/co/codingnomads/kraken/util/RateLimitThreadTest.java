package co.codingnomads.kraken.util;


import co.codingnomads.kraken.exception.RateLimitException;
import co.codingnomads.kraken.exception.UnkownException;
import co.codingnomads.kraken.model.ApiAuthentication;
import co.codingnomads.kraken.model.KrakenRequestEnum;
import co.codingnomads.kraken.model.OutputWrapper;
import co.codingnomads.kraken.service.CallCounter;
import co.codingnomads.kraken.service.GenericRequestHandler;

/**
 * @author Kevin Neag
 */
public class RateLimitThreadTest implements Runnable{

    Thread thrd;
    ApiAuthentication apiAuthentication;

    // Construct a new thread.
    public RateLimitThreadTest(String name, ApiAuthentication key) {
        thrd = new Thread(this, name);
        this.apiAuthentication = apiAuthentication;
        thrd.start(); // start the thread
    }

    // Begin execution of new thread.
    public void run() {
        System.out.println(thrd.getName() + " starting.");

        CallCounter callCounter = new CallCounter();

//        try {
//            callCounter.isUnderRateLimit(key, KrakenRequestEnum.GETSERVERTIME);
//        } catch (RateLimitException e) {
//            e.printStackTrace();
//        }

        GenericRequestHandler handler = new GenericRequestHandler();
        OutputWrapper serverTime = null;
        try {
            serverTime = handler.callAPI(KrakenRequestEnum.GETSERVERTIME, null, apiAuthentication);
        } catch (UnkownException e) {
            e.printStackTrace();
        } catch (RateLimitException e) {
            e.printStackTrace();
        }
        System.out.println(thrd.getName() + " terminating.");
    }
}