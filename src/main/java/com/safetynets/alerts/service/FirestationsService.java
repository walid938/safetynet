package com.safetynets.alerts.service;

import com.safetynets.alerts.model.Firestations;
import com.safetynets.alerts.model.FirestationsArea;

public interface FirestationsService {

	FirestationsArea getFirestationArea(int firestationNumber);
	 
	Firestations saveFirestation(Firestations model);
	
	 boolean deleteFirestation(Firestations model);
	
}
