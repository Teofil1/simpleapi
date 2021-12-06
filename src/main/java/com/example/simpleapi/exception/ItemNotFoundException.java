package com.example.simpleapi.exception;

public class ItemNotFoundException extends Exception {

    public ItemNotFoundException(String id) {
        super("Could not find item with id " + id);
    }
}
