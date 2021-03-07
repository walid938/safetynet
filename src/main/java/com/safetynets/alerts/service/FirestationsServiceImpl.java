package com.safetynets.alerts.service;

import com.safetynets.alerts.exception.ControllerAdvisor;
import com.safetynets.alerts.exception.NoFirestationFound;
import com.safetynets.alerts.exception.NoPersonFoundAddress;
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
    
    @Autowired
    private MedicalRecordsService medicalRecordsService;

   
	
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

	
	
	
	@Override
    public List<Integer> getStationAddress(String address) {
        List<Integer> stationNumber = new ArrayList<>();

        for (Firestations fireStation : jsondataAccess.getFirestations()) {
            if (address.compareTo(fireStation.getAddress()) == 0) {
                stationNumber.add(fireStation.getStation());
            }
        }
        return stationNumber;
    }



	@Override
    public Fire getPersonInfosAddressFire(String address) {
        List<PersonInfo> fireList = new ArrayList<>();
        List<Integer> stationNumber = getStationAddress(address);

        for (Person person : jsondataAccess.getPersons()) {
            if (person.getAddress().equals(address)) {
            	fireList.add(new PersonInfo(person.getFirstName(), person.getLastName(),
                        null, null, null, person.getPhone(), null, null,
                        jsondataAccess.getAgeFromPerson(person),
                        medicalRecordsService.getMedicationsPerson(person),
                        medicalRecordsService.getAllergiesPerson(person), 0));
            }
        }
        if (CollectionUtils.isNotEmpty(fireList)) {
            log.info("Request get person information with address successful!");
            return new Fire(stationNumber, fireList);
        }
        log.info("Request get person information with address failed.");
        throw new NoPersonFoundAddress(address);
    }

	

	public List<StationInfo> getPersonInfoStationsList(List<Integer> stations) {
        List<StationInfo> infoByStationList = new ArrayList<>();
        int stationCounterRequest = 0;

        if (stations != null) {
            for (int stationNumber : stations) {
                List<AddressInfo> infoByAddressList = new ArrayList<>();
                int index;

                for (Person person : jsondataAccess.getPersons()) {
                    List<Integer> stationArr = getStationAddress(person.getAddress());
                    if (isPartOfStation(stations.get(stationCounterRequest), stationArr)) {
                        PersonInfo personInfo = new PersonInfo(person.getFirstName(), person.getLastName(),
                                null, null, null, person.getPhone(), null,
                                null, jsondataAccess.getAgeFromPerson(person),
                                medicalRecordsService.getMedicationsPerson(person),
                                medicalRecordsService.getAllergiesPerson(person), 0);
                        if ((index = InfoByAddressAlreadyExist(infoByAddressList, person)) != -1) {
                            AddressInfo infoByAddress = infoByAddressList.get(index);
                            infoByAddress.addPerson(personInfo);
                        } else {
                            List<PersonInfo> InfoPersonList = new ArrayList<>();
                            InfoPersonList.add(personInfo);
                            infoByAddressList.add(new AddressInfo(person.getAddress(), InfoPersonList));
                        }

                    }
                }
                stationCounterRequest++;
                infoByStationList.add(new StationInfo(infoByAddressList, stationNumber));
            }
        }
        List<Integer> nbEmptyStationList;
        if ((nbEmptyStationList = checkEmptyStation(infoByStationList)) == null) {
            log.info("Request get person information with station list successful!");
            return infoByStationList;
        }
        log.info("Request get person information with station list failed.");
        throw new NoFirestationFound(nbEmptyStationList);
    }

    private List<Integer> checkEmptyStation(List<StationInfo> infoByStationList) {
        List<Integer> nbEmptyStationList = new ArrayList<>();

        if (infoByStationList != null) {
            for (StationInfo infoByStation : infoByStationList) {
                if (CollectionUtils.isEmpty(infoByStation.getListInfo())) {
                    nbEmptyStationList.add(infoByStation.getStation());
                }
            }
            if (CollectionUtils.isNotEmpty(nbEmptyStationList)) return nbEmptyStationList;
        }
        return null;
    }

    private boolean isPartOfStation(int station, List<Integer> stationArr) {
        for (Integer stationNumber : stationArr) {
            if (station == stationNumber)
                return true;
        }
        return false;
    }

    private int InfoByAddressAlreadyExist(List<AddressInfo> infoByAddressList, Person person) {
        if (infoByAddressList.size() != 0) {
            for (AddressInfo infoByAddress : infoByAddressList) {
                if (infoByAddress.getAddress().equals(person.getAddress())) {
                    return infoByAddressList.indexOf(infoByAddress);
                }
            }
        }
        return -1;
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
