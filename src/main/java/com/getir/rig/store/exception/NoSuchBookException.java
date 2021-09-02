package com.getir.rig.store.exception;

public class NoSuchBookException extends  RuntimeException{

    public NoSuchBookException() {
        super("There is no record with given id...");
    }

}
