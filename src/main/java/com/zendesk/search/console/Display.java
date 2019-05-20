package com.zendesk.search.console;

/**
 * Created by @author Sankash on 5/18/2019
 */


public interface Display<T> {
    /**
     * This method displays writes the result to console
     *
     * @param result
     */
    void show(T result);
}
