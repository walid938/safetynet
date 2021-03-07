package com.safetynets.alerts.service;

import com.safetynets.alerts.exception.ControllerAdvisor;
import com.safetynets.alerts.exception.*;
import com.safetynets.alerts.model.Person;
import com.safetynets.alerts.model.ChildInfoFamily;
import com.safetynets.alerts.model.PersonInfo;
import com.safetynets.alerts.repository.JsonDataAccess;

import org.apache.commons.collections.CollectionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class PersonsServiceImpl implements PersonsService {

    private static final Logger log = LogManager.getLogger(ControllerAdvisor.class);

    @Autowired
    private JsonDataAccess jsonDataAccess;

    @Autowired
    private MedicalRecordsService medicalRecordsService;

    public ChildInfoFamily getChildInfoFamilyAddress(String address) {
        List<PersonInfo> listChild = new ArrayList<>();
        List<PersonInfo> listAdult = new ArrayList<>();

        for (Person person : jsonDataAccess.getPersonsByAddress(address)) {
        	PersonInfo personInfo = new PersonInfo(person.getFirstName(), person.getLastName(),
                    null, null, null, null, null, null,
                    jsonDataAccess.getAgeFromPerson(person), null, null, 0);
            if (personInfo.getAge() < 19) {
                listChild.add(personInfo);
            } else {
                listAdult.add(personInfo);
            }
        }
        if (CollectionUtils.isNotEmpty(listChild)) {
            log.info("Request get child alerts successful!");
            return new ChildInfoFamily(address, listChild, listAdult);
        }
        log.info("Request get child alerts failed.");
        throw new NoChildFoundAddress(address);
    }

    
    
    @Override
    public List<PersonInfo> getPersonInfo(String firstName, String lastName) {
        List<Person> allPersons = jsonDataAccess.getPersons();
        List<PersonInfo> PersonInfoLsit = allPersons.stream()
            .filter(person -> {
                boolean lastNameEquals = person.getLastName().equalsIgnoreCase(lastName) ;
                boolean firstNameEquals = !ObjectUtils.isEmpty(lastName) ? person.getFirstName().equalsIgnoreCase(firstName)  : true;
                return lastNameEquals || firstNameEquals;
            })
            .map(p -> new PersonInfo()
                .setFirstName(p.getFirstName())
                .setLastName(p.getLastName())
                .setAddress(p.getAddress())
                .setCity(p.getCity())
                .setZip(p.getZip())
                .setEmail(p.getEmail())
                .setStation(0)
                .setAge(jsonDataAccess.getAgeFromPerson(p))
                .setMedications(medicalRecordsService.getMedicationsPerson(p))
                .setAllergies(medicalRecordsService.getAllergiesPerson(p))
            )
            .collect(Collectors.toList());

        if (CollectionUtils.isNotEmpty(PersonInfoLsit)) {
            log.info("Request get full information successful!");
            return PersonInfoLsit;
        }
        log.info("Request get full information successful!");
        if (firstName == null || firstName.isEmpty())
            throw new NoPersonFoundName(lastName);
        throw new NoPersonFoundFirstNameAndName(firstName, lastName);
    }
    
    public List<String> getEmails(String city) {
        List<String> emailList = new ArrayList<>();

        for (Person person : jsonDataAccess.getPersons()) {
            if (person.getCity().compareTo(city) == 0) {
                emailList.add(person.getEmail());
            }
        }
        if (CollectionUtils.isNotEmpty(emailList)) {
            log.info("Request get email successful!");
            return emailList;
        }
        log.info("Request get email failed.");
        throw new NoPersonFound();
    }
 	

    public Person savePerson(Person model) {
        Person result = jsonDataAccess.savePerson(model);
        if (result != null) log.info("Request save person successful");
        log.info("Request save person failed");
        return result;
    }

    public Person updatePerson(Person model) {
        Person result = jsonDataAccess.updatePerson(model);
        if (result != null) log.info("Request update person sucessful");
        log.info("Request update person failed");
        return result;
    }

    public boolean deletePerson(Person model) {
        boolean result = jsonDataAccess.deletePerson(model);
        if (result) log.info("Request delete person successful.");
        log.info("Request delete person failed.");
        return result;
    }
}
	

