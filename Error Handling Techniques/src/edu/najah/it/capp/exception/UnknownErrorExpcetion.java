package edu.najah.it.capp.exception;

public class UnknownErrorExpcetion extends ProtocolException{
    public UnknownErrorExpcetion(String message) {
        super(message);
    }
    @Override
    public String getMessage() {
        String message = super.getMessage();
        return "Unable to release the connection: " + message;
    }
}
