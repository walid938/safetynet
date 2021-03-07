package com.safetynets.alerts.controller;


import com.safetynets.alerts.service.PersonsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmailController {

   
    @Autowired
    private PersonsService personsService;

    
    @GetMapping(value = "communityEmail", produces = "application/json")
    public List<String> getEmails(@RequestParam String city) {
        return personsService.getEmails(city);
    }
    
}