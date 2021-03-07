package com.safetynets.alerts.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.safetynets.alerts.util.View;
import com.safetynets.alerts.model.*;
import com.safetynets.alerts.service.FirestationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class FloodController {

    @Autowired
    private FirestationsService firestationsService;


    @JsonView(View.FloodStations.class)
    @GetMapping(value = "flood/stations", produces = "application/json")
    public List<StationInfo> getPersonInfoByStationsList(@RequestParam List<Integer> stations) {
        return firestationsService.getPersonInfoStationsList(stations);
    }
    
}