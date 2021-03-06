package com.safetynets.alerts.service;

import com.safetynets.alerts.exception.ControllerAdvisor;
import com.safetynets.alerts.exception.NoFirestationFound;
import com.safetynets.alerts.model.Firestations;
import com.safetynets.alerts.model.Person;
import com.safetynets.alerts.model.*;
import com.safetynets.alerts.repository.JsonDataAccess;	
import org.apache.commons.collections.CollectionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FirestationsServiceImpl implements FirestationsService {

    private static final Logger log = LogManager.getLogger(ControllerAdvisor.class);

    @Autowired
    private JsonDataAccess jsondataAccess;

   
	
	public FirestationsArea getFirestationArea(int firestationNumber) {
        List<Person> personList = jsondataAccess.getPersonsByFirestationNumber(firestationNumber);
        long nbChildren = personList.stream().filter(p -> jsondataAccess.getAgeFromPerson(p) <= 18).count();
        long nbAdults = personList.stream().filter(p -> jsondataAccess.getAgeFromPerson(p) > 18).count();
        if (CollectionUtils.isNotEmpty(personList)) {
            log.info("Request get firestation area successful!");
            return new FirestationsArea(personList, nbAdults, nbChildren);
        }
        log.info("Request get firestation area failed.");
        throw new NoFirestationFound(List.of(firestationNumber));
    }


	
	@Override
	    public List<String> getPhoneAlert(int firestationNumber) {
	        List<String> phoneList = new ArrayList<>();

	        for (Person person : jsondataAccess.getPersons()) {
	            if (jsondataAccess.getNbStationByAddressFromPerson(person) == firestationNumber) {
	            	phoneList.add(person.getPhone());
	            }
	        }
	        if (CollectionUtils.isNotEmpty(phoneList)) {
	            log.info("Request get phone alert successful!");
	            return phoneList;
	        }
	        log.info("Request get phone alert failed.");
	        throw new NoFirestationFound(List.of(firestationNumber));
	    }
	
	
	
  
    public Firestations saveFirestation(Firestations model) {
        Firestations result = jsondataAccess.saveFirestation(model);
        if (result != null) log.info("Request save firestation successful!");
        log.info("Request save firestation failed.");
        return result;
    }

    public boolean deleteFirestation(Firestations model) {
        boolean result = jsondataAccess.deleteFirestation(model);
        if (result) log.info("Request delete firestation successful!");
        log.info("Request delete firestation failed.");
        return result;
    }
}
