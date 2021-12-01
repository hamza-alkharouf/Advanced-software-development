package edu.najah.it.capp.exception;

public class ConnectionAlreadyReleasedException extends  ProtocolException{
    public ConnectionAlreadyReleasedException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        String message = super.getMessage();
        return message;
    }

}
