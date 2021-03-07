package com.safetynets.alerts.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.safetynets.alerts.util.View;
import com.safetynets.alerts.model.*;
import com.safetynets.alerts.service.PersonsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PersonInfoController {

   
    @Autowired
    private PersonsService personsService;

    @JsonView(View.PersonInfo.class)
    @GetMapping(value = "personInfo", produces = "application/json")
    public List<PersonInfo> getPersonInfo(
            @RequestParam(value = "firstName", required = false) String firstName,
            @RequestParam(value = "lastName") String lastName) {
        return personsService.getPersonInfo(firstName, lastName);
    }

    
}