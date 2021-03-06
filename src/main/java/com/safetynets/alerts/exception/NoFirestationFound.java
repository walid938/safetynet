package com.safetynets.alerts.exception;

import java.util.List;

public class NoFirestationFound extends RuntimeException {

    private final List<Integer> firestationNb;

    public NoFirestationFound(List<Integer> firestationNb){
        super("No Firestation(s) found for number : " + firestationNb + " !");
        this.firestationNb = firestationNb;
    }

    public List<Integer> getFirestationNb() {
        return firestationNb;
    }
}