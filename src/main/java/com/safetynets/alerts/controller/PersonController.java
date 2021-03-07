package com.safetynets.alerts.controller;


import com.safetynets.alerts.model.*;
import com.safetynets.alerts.service.PersonsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;



@RestController
public class PersonController {
	
	@Autowired
    private PersonsService personsService;
   
	@PostMapping(
            value = "/person", consumes = "application/json", produces = "application/json")
    public Person createPerson(@RequestBody Person person) {
        return personsService.savePerson(person);
    }

    @PutMapping(
            value = "/person", consumes = "application/json", produces = "application/json")
    public Person updatePerson(@RequestBody Person person) {
        return personsService.updatePerson(person);
    }

    @DeleteMapping(
            value = "/person", consumes = "application/json")
    public boolean deletePerson(@RequestBody Person person) {
        return personsService.deletePerson(person);
    }

    
}