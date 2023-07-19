package com.tradepal.TradePalApp.exception;

public class TradeInviteAlreadySentException extends RuntimeException{
    public TradeInviteAlreadySentException(String message){super(message);}
    public TradeInviteAlreadySentException(String message, Throwable cause){super(message, cause);}
}
