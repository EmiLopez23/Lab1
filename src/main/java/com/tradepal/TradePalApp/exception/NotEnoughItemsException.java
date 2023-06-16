package com.tradepal.TradePalApp.exception;

public class NotEnoughItemsException extends RuntimeException{

    public NotEnoughItemsException(String message){super(message);}

    public NotEnoughItemsException(String message, Throwable cause){super(message, cause);}
}
