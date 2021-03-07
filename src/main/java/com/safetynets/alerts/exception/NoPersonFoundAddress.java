package com.safetynets.alerts.exception;

public class NoPersonFoundAddress extends RuntimeException {

    private final String address;

    public NoPersonFoundAddress(String address) {
        super("No person(s) found for address : " + address + " !");
        this.address = address;
    }

    public String getAddress() {
        return address;
    }
}