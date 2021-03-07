package com.safetynets.alerts.service;

import com.safetynets.alerts.exception.ControllerAdvisor;
import com.safetynets.alerts.model.MedicalRecords;
import com.safetynets.alerts.model.Person;
import com.safetynets.alerts.repository.JsonDataAccess;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicalRecordsServiceImpl implements MedicalRecordsService {

    private static final Logger log = LogManager.getLogger(ControllerAdvisor.class);

    @Autowired
    private JsonDataAccess jsondataAccess;


	@Override
    public List<String> getMedicationsPerson(Person person) {
        for (MedicalRecords medicalRecords : jsondataAccess.getMedicalrecords()) {
            if (person.getFirstName().equals(medicalRecords.getFirstName()) &&
                    person.getLastName().equals(medicalRecords.getLastName())) {
                return medicalRecords.getMedications();
            }
        }
        return null;
    }


	@Override
    public List<String> getAllergiesPerson(Person person) {
        for (MedicalRecords medicalRecords : jsondataAccess.getMedicalrecords()) {
            if (person.getFirstName().equals(medicalRecords.getFirstName()) &&
                    person.getLastName().equals(medicalRecords.getLastName())) {
                return medicalRecords.getAllergies();
            }
        }
        return null;
    }
	
	
	public MedicalRecords saveMedicalRecords(MedicalRecords model) {
	        MedicalRecords result = jsondataAccess.saveMedicalRecords(model);
	        if (result != null) log.info("Request save medical records successful!");
	        log.info("Request save medical records failed.");
	        return result;
	    }


		public MedicalRecords updateMedicalRecords(MedicalRecords model) {
	        MedicalRecords result = jsondataAccess.updateMedicalRecords(model);
	        if (result != null) log.info("Request update medical records successful!");
	        log.info("Request update medical records failed.");
	        return result;
	    }


		public boolean deleteMedicalRecords(MedicalRecords model) {
	        boolean result = jsondataAccess.deleteMedicalRecords(model);
	        if (result) log.info("Request delete MedicalRecords successful.");
	        log.info("Request delete MedicalRecords failed.");
	        return result;
	    }
}