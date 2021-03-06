package com.safetynets.alerts.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.safetynets.alerts.util.View;
import com.safetynets.alerts.service.FirestationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PhoneAlertController{

    @Autowired
    private FirestationsService firestationsService;


    @JsonView(View.Phone.class)
    @GetMapping(value = "phoneAlert", produces = "application/json")
    public List<String> getPhoneAlertFromFirestations(@RequestParam int firestation) {
        return firestationsService.getPhoneAlert(firestation);
    }
  
    
}