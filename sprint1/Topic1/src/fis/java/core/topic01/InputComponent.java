package fis.java.core.topic01;

public class InputComponent {

    IValidation validation;
    String data;

    public void setData(String data) {
        this.data = data;
    }

    public void display() {
    }

    public void setValidation(IValidation validation) {
    }

    public boolean validate() {
        return true;
    }
}
