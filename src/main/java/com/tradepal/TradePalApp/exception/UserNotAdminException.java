package com.tradepal.TradePalApp.exception;

public class UserNotAdminException extends RuntimeException{

    public UserNotAdminException(String message){super(message);}

    public UserNotAdminException(String message, Throwable cause){super(message,cause);}
}
