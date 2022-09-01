package com.thecms.config.exception;

import lombok.Data;

@Data
public class NotFoundUserException extends RuntimeException{
    private String message;

    public NotFoundUserException(String message){
        this.message = message;
    }

}
