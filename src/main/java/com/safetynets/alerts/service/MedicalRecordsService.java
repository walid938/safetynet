package com.safetynets.alerts.service;

import java.util.List;

import com.safetynets.alerts.model.MedicalRecords;
import com.safetynets.alerts.model.Person;

public interface MedicalRecordsService {

	List<String> getMedicationsPerson(Person person);

	List<String> getAllergiesPerson(Person person);

	MedicalRecords saveMedicalRecords(MedicalRecords model);

	MedicalRecords updateMedicalRecords(MedicalRecords model);

	boolean deleteMedicalRecords(MedicalRecords model);

}
