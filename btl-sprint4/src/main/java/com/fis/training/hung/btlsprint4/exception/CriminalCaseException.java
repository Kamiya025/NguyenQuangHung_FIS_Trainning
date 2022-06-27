package com.fis.training.hung.btlsprint4.exception;

public class CriminalCaseException extends ApplicationException{

    public CriminalCaseException(String message) {
        super(message);
    }

    @Override
    public String getErrorCode() {
        return null;
    }
}
