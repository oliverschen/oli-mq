package com.github.oliverschen.olimq.exception;

/**
 * @author ck
 */
public class OliException extends RuntimeException {


    public OliException() {
        super();
    }

    public OliException(String message) {
        super(message);
    }

    public OliException(String message, Throwable cause) {
        super(message, cause);
    }

    public OliException(Throwable cause) {
        super(cause);
    }

    protected OliException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
