package com.tradepal.TradePalApp.exception;

public class GameDoesNotExistException extends RuntimeException{
    public GameDoesNotExistException(String message){super(message);}
    public GameDoesNotExistException(String message, Throwable cause){super(message, cause);}
}
