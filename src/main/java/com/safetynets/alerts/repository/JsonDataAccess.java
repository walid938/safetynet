package com.safetynets.alerts.repository;

import com.safetynets.alerts.model.Firestations;
import com.safetynets.alerts.model.MedicalRecords;
import com.safetynets.alerts.model.Person;

import java.util.List;

public interface JsonDataAccess {
    int getNbStationByAddressFromPerson(Person person);

    int getAgeFromPerson(Person person);

    List<Person> getPersonsByFirestationNumber(int firestationNumber);

    List<Person> getPersonsByAddress(String address);

    int getAgeFromBirthdate(String birthdate);

    List<Firestations> getFirestations();

    List<Person> getPersons();

    List<MedicalRecords> getMedicalrecords();

    Firestations saveFirestation(Firestations model);

    boolean deleteFirestation(Firestations model);
}