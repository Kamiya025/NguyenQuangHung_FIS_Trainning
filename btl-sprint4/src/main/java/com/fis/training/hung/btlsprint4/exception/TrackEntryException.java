package com.fis.training.hung.btlsprint4.exception;

public class TrackEntryException extends ApplicationException{

    public TrackEntryException(String message) {
        super(message);
    }

    @Override
    public String getErrorCode() {
        return null;
    }
}
