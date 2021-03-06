package com.safetynets.alerts.service;

import java.util.List;

import com.safetynets.alerts.model.Firestations;
import com.safetynets.alerts.model.FirestationsArea;

public interface FirestationsService {

	FirestationsArea getFirestationArea(int firestationNumber);
	 
	Firestations saveFirestation(Firestations model);
	
	 boolean deleteFirestation(Firestations model);

	

	List<String> getPhoneAlert(int firestationNumber);
	
}
