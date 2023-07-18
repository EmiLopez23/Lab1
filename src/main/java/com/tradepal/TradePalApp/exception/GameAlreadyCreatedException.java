package com.tradepal.TradePalApp.exception;

public class GameAlreadyCreatedException extends RuntimeException{

    public GameAlreadyCreatedException (String message){super(message);}
    public GameAlreadyCreatedException (String message, Throwable cause){super(message, cause);}
}
