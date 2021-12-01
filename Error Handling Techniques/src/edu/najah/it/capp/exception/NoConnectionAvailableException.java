package edu.najah.it.capp.exception;

public class NoConnectionAvailableException extends ProtocolException{
    public NoConnectionAvailableException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        String message = super.getMessage();
        return "No connection is available: " + message;
    }
}
