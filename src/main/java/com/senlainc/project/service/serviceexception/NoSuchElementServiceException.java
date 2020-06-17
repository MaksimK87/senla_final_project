package com.senlainc.project.service.serviceexception;

public class NoSuchElementServiceException extends Exception {

    public NoSuchElementServiceException() {
    }

    public NoSuchElementServiceException(String message) {
        super(message);
    }

    public NoSuchElementServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoSuchElementServiceException(Throwable cause) {
        super(cause);
    }
}
