package com.safetynets.alerts.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.safetynets.alerts.util.View;
import com.safetynets.alerts.model.Firestations;
import com.safetynets.alerts.model.*;
import com.safetynets.alerts.service.FirestationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class FirestationsController {

    @Autowired
    private FirestationsService firestationsService;


    @JsonView(View.FirestationById.class)
    @GetMapping(value = "/firestation", produces = "application/json")
    public FirestationsArea getFirestationsByID(@RequestParam int stationNumber) {
        return firestationsService.getFirestationArea(stationNumber);
    }
    

    @PostMapping(
            value = "/firestation", consumes = "application/json", produces = "application/json")
    public Firestations saveFirestation(@RequestBody Firestations firestations) {
        return firestationsService.saveFirestation(firestations);
    }

    @DeleteMapping(
            value = "/firestation", consumes = "application/json")
    public boolean deleteFirestation(@RequestBody Firestations firestations) {
        return firestationsService.deleteFirestation(firestations);
    }
    
}