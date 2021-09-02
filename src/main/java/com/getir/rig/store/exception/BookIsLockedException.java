package com.getir.rig.store.exception;

public class BookIsLockedException  extends  RuntimeException{
    public BookIsLockedException(){
        super("Book is being ordered by some one...");
    }
}
