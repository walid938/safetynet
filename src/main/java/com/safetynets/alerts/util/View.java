package com.safetynets.alerts.util;

public class View {
    public interface FirstName {
    }

    public interface LastName {
    }

    public interface Address {
    }

    public interface Phone {
    }

    public interface Email {
    }

    public interface FirestationById extends FirstName, LastName, Address, Phone {
    }
}