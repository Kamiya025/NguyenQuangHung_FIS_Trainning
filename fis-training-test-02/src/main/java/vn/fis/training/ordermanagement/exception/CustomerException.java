package vn.fis.training.ordermanagement.exception;

public class CustomerException extends ApplicationException{

    public CustomerException(String message) {
        super(message);
    }

    @Override
    public String getErrorCode() {
        return null;
    }
}
