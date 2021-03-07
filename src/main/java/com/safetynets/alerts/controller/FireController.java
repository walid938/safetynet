package com.safetynets.alerts.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.safetynets.alerts.util.View;

import com.safetynets.alerts.model.*;
import com.safetynets.alerts.service.FirestationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
public class FireController {

    @Autowired
    private FirestationsService firestationsService;

    
    
    @JsonView(View.Fire.class)
    @GetMapping(value = "fire", produces = "application/json")
    public Fire getPersonInfosAddressFire(@RequestParam String address) {
        return firestationsService.getPersonInfosAddressFire(address);
    }

  
    
}