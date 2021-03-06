package com.safetynets.alerts.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.safetynets.alerts.util.View;
import com.safetynets.alerts.util.View.ChildInfo;
import com.safetynets.alerts.model.*;
import com.safetynets.alerts.service.PersonsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;



@RestController
public class ChildController {

   

    @Autowired
    private PersonsService personsService;
 
    @JsonView(View.ChildInfo.class)
    @GetMapping(value = "/childAlert", produces = "application/json")
    public ChildInfoFamily getChildInfoFamilyFromAddress(@RequestParam String address) {
        return personsService.getChildInfoFamilyAddress(address);
    }
    
}
