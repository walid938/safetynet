package com.safetynets.alerts.exception;

public class NoPersonFoundFirstNameAndName extends RuntimeException {

    private final String firsName;

    private final String lastName;

    public NoPersonFoundFirstNameAndName(String firsName, String lastName) {
        super("No person(s) named : " + firsName + " " + lastName + " found!");
        this.firsName = firsName;
        this.lastName = lastName;
    }

    public String getFirsName() {
        return firsName;
    }

    public String getLastName() {
        return lastName;
    }
}