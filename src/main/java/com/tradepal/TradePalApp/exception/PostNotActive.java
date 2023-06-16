package com.tradepal.TradePalApp.exception;

public class PostNotActive extends RuntimeException{

    public PostNotActive(String message){super(message);}

    public PostNotActive(String message, Throwable cause){super(message, cause);}
}
