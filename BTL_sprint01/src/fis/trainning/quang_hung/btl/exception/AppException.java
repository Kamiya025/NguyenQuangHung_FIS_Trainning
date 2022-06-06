package fis.trainning.quang_hung.btl.exception;

public abstract class AppException extends RuntimeException{

    public AppException(String message) {
        super(message);
    }

    public abstract String getErrorCode();

}
