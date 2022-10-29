package com.example.nursinghomeapplication.service.ex;

public class LimitExceededException extends SecurityException{
    public LimitExceededException() {
        super();
    }

    public LimitExceededException(String s) {
        super(s);
    }

    public LimitExceededException(String message, Throwable cause) {
        super(message, cause);
    }

    public LimitExceededException(Throwable cause) {
        super(cause);
    }
}
