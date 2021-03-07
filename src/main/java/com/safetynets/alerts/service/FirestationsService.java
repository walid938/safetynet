package com.safetynets.alerts.service;

import java.util.List;

import com.safetynets.alerts.model.Fire;
import com.safetynets.alerts.model.Firestations;
import com.safetynets.alerts.model.FirestationsArea;
import com.safetynets.alerts.model.StationInfo;

public interface FirestationsService {

	FirestationsArea getFirestationArea(int firestationNumber);
	 
	Firestations saveFirestation(Firestations model);
	
	 boolean deleteFirestation(Firestations model);

	

	List<String> getPhoneAlert(int firestationNumber);

	List<Integer> getStationAddress(String address);
	
	Fire getPersonInfosAddressFire(String address);

	List<StationInfo> getPersonInfoStationsList(List<Integer> stations);
	
	
}
