package co.codingnomads.kraken.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Arrays;

/**
 * Created by Thomas Leruth on 11/29/17
 */

public class OutputWrapper<T> {

    String[] error;

    T result;

    public String[] getError() {
        return error;
    }

    public void setError(String[] error) {
        this.error = error;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public String toString() {
        return "OutputWrapper{" +
                "error=" + Arrays.toString(error) +
                ", result=" + result +
                '}';
    }

    public String printError() {
        return "error=" + Arrays.toString(error);
    }

    public OutputWrapper() {
    }

    @JsonCreator
    public OutputWrapper(@JsonProperty("result") T result, @JsonProperty("error") String[] error) {
        this.result = result;
        this.error = error;
    }

//    public boolean isSuccess() {
//        return error.length == 0;
//    }
//
//    @Override
//    public String toString() {
//        return String.format("KrakenResult[%s: %s]", isSuccess() ? "OK" : "error", isSuccess() ? result.toString() : error);
//    }

}
