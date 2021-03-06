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


import java.util.ArrayList;
import java.util.List;


@Service
public class PersonsServiceImpl implements PersonsService {

    private static final Logger log = LogManager.getLogger(ControllerAdvisor.class);

    @Autowired
    private JsonDataAccess jsonDataAccess;

   

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

 	

   
}
