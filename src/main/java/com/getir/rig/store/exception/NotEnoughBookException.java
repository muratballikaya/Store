package com.getir.rig.store.exception;

public class NotEnoughBookException extends RuntimeException {

    public NotEnoughBookException(){
        super("There are not enough book to update.");
    }
}
