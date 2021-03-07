package com.safetynets.alerts.exception;

public class NoPersonFound extends RuntimeException {

    public NoPersonFound() {
        super("No person(s) found!");
    }

}