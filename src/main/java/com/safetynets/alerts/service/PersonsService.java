package com.safetynets.alerts.service;


import java.util.List;

import com.safetynets.alerts.model.ChildInfoFamily;
import com.safetynets.alerts.model.Person;
import com.safetynets.alerts.model.PersonInfo;


public interface PersonsService {

	ChildInfoFamily getChildInfoFamilyAddress(String address);

	List<PersonInfo> getPersonInfo(String firstName, String lastName);
	
	 List<String> getEmails(String city);

	Person savePerson(Person model);

	Person updatePerson(Person model);

	boolean deletePerson(Person model);

	

   
    
}

