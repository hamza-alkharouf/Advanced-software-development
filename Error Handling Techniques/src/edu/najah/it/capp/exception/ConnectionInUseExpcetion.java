package edu.najah.it.capp.exception;

public class ConnectionInUseExpcetion extends ProtocolException{
    public ConnectionInUseExpcetion(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        String message = super.getMessage();
        return "Connection is inuse, you can’t release now" +message;
    }
}
