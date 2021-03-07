package com.safetynets.alerts.controller;


import com.safetynets.alerts.model.*;
import com.safetynets.alerts.service.MedicalRecordsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
public class MedicalRecordsController {

	   @Autowired
	    private MedicalRecordsService medicalRecordsService;


	   @PostMapping(
	            value = "/medicalRecord", consumes = "application/json", produces = "application/json")
	    public MedicalRecords createMedicalRecords(@RequestBody MedicalRecords medicalRecords) {
	        return medicalRecordsService.saveMedicalRecords(medicalRecords);
	    }

	    @PutMapping(
	            value = "/medicalRecord", consumes = "application/json", produces = "application/json")
	    public MedicalRecords updateMedicalRecords(@RequestBody MedicalRecords medicalRecords) {
	        return medicalRecordsService.updateMedicalRecords(medicalRecords);
	    }

	    @DeleteMapping(
	            value = "/medicalRecord", consumes = "application/json")
	    public boolean deleteMedicalRecords(@RequestBody MedicalRecords medicalRecords) {
	        return medicalRecordsService.deleteMedicalRecords(medicalRecords);
	    }

    
}