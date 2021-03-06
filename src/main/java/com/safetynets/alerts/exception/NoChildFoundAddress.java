package com.safetynets.alerts.exception;

public class NoChildFoundAddress extends RuntimeException {

    
	private final String address;

    public NoChildFoundAddress(String address) {
        super("No child(ren) found for address : " + address + " !");
        this.address = address;
    }

    public String getAddress() {
        return address;
    }
}