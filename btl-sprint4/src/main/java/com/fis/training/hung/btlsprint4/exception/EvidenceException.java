package com.fis.training.hung.btlsprint4.exception;

public class EvidenceException extends ApplicationException{

    public EvidenceException(String message) {
        super(message);
    }

    @Override
    public String getErrorCode() {
        return null;
    }
}
