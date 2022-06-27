package com.fis.training.hung.btlsprint4.exception;

public class StorageException extends ApplicationException{

    public StorageException(String message) {
        super(message);
    }

    @Override
    public String getErrorCode() {
        return null;
    }
}
