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
    
    public interface InfoFirstName {
    }

    public interface InfoLastName {
    }

    public interface InfoAddress {
    }

    public interface InfoCity {
    }

    public interface InfoZip {
    }

    public interface InfoPhone {
    }

    public interface InfoEmail {
    }

    public interface InfoBirthdate {
    }

    public interface InfoAge {
    }

    public interface InfoMedications {
    }

    public interface InfoAllergies {
    }

    public interface InfoStation {
    }
    public interface ChildInfo extends InfoFirstName, InfoLastName, InfoAge {
    }

}