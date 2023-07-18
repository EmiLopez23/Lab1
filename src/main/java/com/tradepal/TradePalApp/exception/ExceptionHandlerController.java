package com.tradepal.TradePalApp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<String> handleUserNotFoundException(UserNotFoundException ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }


    @ExceptionHandler(UserRegisterException.class)
    public ResponseEntity<String> handleRegisterException(UserRegisterException userExc){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(userExc.getMessage());
    }

    @ExceptionHandler(TradeInviteAlreadyAccepted.class)
    public ResponseEntity<String> handleTradeInviteAlreadyAcceptedException(TradeInviteAlreadyAccepted ex){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(PostNotActive.class)
    public ResponseEntity<String> handlePostNotActiveException(PostNotActive ex){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(NotEnoughItemsException.class)
    public ResponseEntity<String> handleNotEnoughItemsException(NotEnoughItemsException ex){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(UserBannedException.class)
    public ResponseEntity<String> handleUserBannedException(UserBannedException ex){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(UserNotAdminException.class)
    public ResponseEntity<String> handleUserNotAdminException(UserNotAdminException ex){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(GameAlreadyCreatedException.class)
    public ResponseEntity<String> handleGameAlreadyCreatedException(GameAlreadyCreatedException ex){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

}
