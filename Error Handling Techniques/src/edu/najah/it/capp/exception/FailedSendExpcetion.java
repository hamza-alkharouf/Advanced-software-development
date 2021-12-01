package edu.najah.it.capp.exception;

public class FailedSendExpcetion extends  ProtocolException{
    public FailedSendExpcetion(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        String message = super.getMessage();
        return  "Unexpcted error in protcol: " + message;

    }
}
