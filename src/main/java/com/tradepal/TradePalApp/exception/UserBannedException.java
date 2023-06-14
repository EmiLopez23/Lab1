package com.tradepal.TradePalApp.exception;

public class UserBannedException extends RuntimeException {

    public UserBannedException (String message){super(message);}

    public UserBannedException (String message, Throwable cause){super(message, cause);}
}
