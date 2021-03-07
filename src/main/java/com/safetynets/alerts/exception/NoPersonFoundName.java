package com.safetynets.alerts.exception;

public class NoPersonFoundName extends RuntimeException {

    private final String name;

    public NoPersonFoundName(String name) {
        super("No person(s) named : " + name + " found!");
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
