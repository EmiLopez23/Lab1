package com.tradepal.TradePalApp.exception;

public class TradeInviteAlreadyAccepted extends RuntimeException{

    public TradeInviteAlreadyAccepted(String message){super(message);}

    public TradeInviteAlreadyAccepted(String message, Throwable cause){super(message, cause);}
}
