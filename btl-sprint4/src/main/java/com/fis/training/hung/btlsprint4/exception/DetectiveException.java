package com.fis.training.hung.btlsprint4.exception;

public class DetectiveException extends ApplicationException{

    public DetectiveException(String message) {
        super(message);
    }

    @Override
    public String getErrorCode() {
        return null;
    }
}
