package com.psu.est.service.exception;

/**
 * Created by danielkarkee on 4/5/16.
 */
public class InvalidAddressException extends RuntimeException {

    public InvalidAddressException() {
    }

    public InvalidAddressException(String message) {
        super(message);
    }

    public InvalidAddressException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidAddressException(Throwable cause) {
        super(cause);
    }

    public InvalidAddressException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
