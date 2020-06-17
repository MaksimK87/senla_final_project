package com.senlainc.project.dao.daoexception;

public class NoSuchElementDAOException extends Exception {

    public NoSuchElementDAOException() {
    }

    public NoSuchElementDAOException(String message) {
        super(message);
    }

    public NoSuchElementDAOException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoSuchElementDAOException(Throwable cause) {
        super(cause);
    }
}
