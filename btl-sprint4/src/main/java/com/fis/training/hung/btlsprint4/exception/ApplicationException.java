package com.fis.training.hung.btlsprint4.exception;

public abstract class ApplicationException extends IllegalArgumentException{

    public ApplicationException(String message) {
        super(message);
    }

    public abstract String getErrorCode();
}
