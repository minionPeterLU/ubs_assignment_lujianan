package com.drawing.model;

public class InvalidItemException extends RuntimeException {
    public InvalidItemException(String message){
        super(message);
    }
}
