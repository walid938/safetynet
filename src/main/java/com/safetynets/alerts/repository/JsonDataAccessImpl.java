package com.safetynets.alerts.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetynets.alerts.model.JsonData;
import com.safetynets.alerts.model.Firestations;
import com.safetynets.alerts.model.MedicalRecords;
import com.safetynets.alerts.model.Person;
import com.safetynets.alerts.repository.JsonDataAccess;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.swing.text.html.Option;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

@Repository
public class JsonDataAccessImpl implements JsonDataAccess {

    private static final Logger logger = LogManager.getLogger(JsonDataAccessImpl.class);

    private ObjectMapper objectMapper;
    private JsonData jsondata;

    @Autowired
    public JsonDataAccessImpl(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public void setDataFile(JsonData jsonData) {
        this.jsondata = jsonData;
    }

    public JsonData loadJsonData() {
        if (jsondata != null) {
            return jsondata;
        }
        try {
            jsondata = objectMapper.readValue(new File("src/main/resources/jsondata.json"), JsonData.class);
            logger.debug("Json correctly mapped!");
        } catch (IOException e) {
            logger.error("Error while JSON mapping!");
            throw new RuntimeException(e);
        }
        return jsondata;
    }

    public int getNbStationByAddressFromPerson(Person person) {
        if (person != null) {
        	loadJsonData();
            return getFirestations()
                    .stream()
                    .filter(fireStation -> person.getAddress().equals(fireStation.getAddress()))
                    .findFirst()
                    .map(Firestations::getStation)
                    .orElse(0);
        }
        return 0;
    }

    public int getAgeFromPerson(Person person) {
        if (person != null) {
            for (MedicalRecords medicalRecords : loadJsonData().getMedicalrecords()) {
                if (Objects.equals(person.getFirstName(), medicalRecords.getFirstName()) &&
                        Objects.equals(person.getLastName(), medicalRecords.getLastName())) {
                    return getAgeFromBirthdate(medicalRecords.getBirthdate());
                }
            }
        }
        return 0;
    }

    public List<Person> getPersonsByFirestationNumber(int firestationNumber) {
        List<Person> result = new ArrayList<>();

        for (Person person : loadJsonData().getPersons()) {
            if (getNbStationByAddressFromPerson(person) == firestationNumber) {
                result.add(person);
            }
        }
        return result;
    }

    public List<Person> getPersonsByAddress(String address) {
        List<Person> result = new ArrayList<>();
        if (address != null) {


            for (Person person : loadJsonData().getPersons()) {
                if (person.getAddress().equals(address)) {
                    result.add(person);
                }
            }
            return result;
        }
        return result;
    }

    public int getAgeFromBirthdate(String birthdate) {
        LocalDate currentDate = LocalDate.now();
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
            formatter = formatter.withLocale(Locale.FRANCE);
            LocalDate birthDate = LocalDate.parse(birthdate, formatter);
            return Period.between(birthDate, currentDate).getYears();
        } catch (DateTimeParseException e) {
            logger.info("Birthdate non valid.");
        } catch (RuntimeException e) {
            logger.info("Birthdate non valid.");
        }
        return 0;
    }

    public List<Firestations> getFirestations() {
        return new ArrayList<>(loadJsonData().getFirestations());
    }

    public List<Person> getPersons() {
        return new ArrayList<>(loadJsonData().getPersons());
    }

    public List<MedicalRecords> getMedicalrecords() {
        return new ArrayList<>(loadJsonData().getMedicalrecords());
    }


    public Firestations saveFirestation(Firestations model) {
        if (model != null) {
            boolean i;
            if (loadJsonData().getFirestations() != null) {
                i = loadJsonData().getFirestations().stream().noneMatch(firestations -> firestations.equals(model));
                if (i) {
                	loadJsonData().getFirestations().add(model);
                    return model;
                }
            } else {
                List<Firestations> firestationsList = new ArrayList<>();
                firestationsList.add(model);
                loadJsonData().setFirestations(firestationsList);
                return model;
            }
        }
        return null;
    }

    public boolean deleteFirestation(Firestations model) {
        if (model != null) {
            if (loadJsonData().getFirestations() != null) {
                return loadJsonData().getFirestations().remove(model);
            } else loadJsonData().setFirestations(new ArrayList<>());
        }
        return false;
    }
    
    @Override
    public Person savePerson(Person model) {
        if (model != null) {
            boolean i;
            if (loadJsonData().getPersons() != null) {
                i = loadJsonData().getPersons().stream().noneMatch(person -> person.equals(model));
                if (i) {
                	loadJsonData().getPersons().add(model);
                    return model;
                }
            } else {
                List<Person> personList = new ArrayList<>();
                personList.add(model);
                loadJsonData().setPersons(personList);
                return model;

            }
        }
        return null;
    }


	@Override
    public Person updatePerson(Person model) {
        if (model != null) {
            if (loadJsonData().getPersons() != null) {
                Optional<Person> personOptionalToUpdate = loadJsonData().getPersons().stream().filter(person -> model.getFirstName().equals(person.getFirstName()) &&
                        model.getLastName().equals(person.getLastName())).findFirst();
                if (personOptionalToUpdate.isPresent()) {
                	loadJsonData().getPersons().set(loadJsonData().getPersons().indexOf(personOptionalToUpdate.get()), model);
                    return model;
                }
            } else loadJsonData().setPersons(new ArrayList<>());
        }
        return null;
    }


	@Override
    public boolean deletePerson(Person model) {
        if (model != null) {
            if (loadJsonData().getPersons() != null) {
                Optional<Person> personOptionalTodelete = loadJsonData().getPersons().stream().filter(person -> model.getFirstName().equals(person.getFirstName()) &&
                        model.getLastName().equals(person.getLastName())).findFirst();
                if (personOptionalTodelete.isPresent()) {
                	loadJsonData().getPersons().remove(personOptionalTodelete.get());
                    return true;
                }
            } else loadJsonData().setPersons(new ArrayList<>());
        }
        return false;
    }

	@Override
    public MedicalRecords saveMedicalRecords(MedicalRecords model) {
        if (model != null) {
            boolean i;
            if (loadJsonData().getMedicalrecords() != null) {
                i = loadJsonData().getMedicalrecords().stream().noneMatch(medicalRecords -> medicalRecords.equals(model));
                if (i) {
                	loadJsonData().getMedicalrecords().add(model);
                    return model;
                }
            } else {
                List<MedicalRecords> medicalRecordsList = new ArrayList<>();
                medicalRecordsList.add(model);
                loadJsonData().setMedicalrecords(medicalRecordsList);
                return model;
            }
        }
        return null;
    }

	@Override
    public MedicalRecords updateMedicalRecords(MedicalRecords model) {
        if (model != null) {
            if (loadJsonData().getMedicalrecords() != null) {
                Optional<MedicalRecords> medicalRecordsOptionalToUpdate = loadJsonData().getMedicalrecords().stream().filter(medicalRecords -> model.getFirstName().equals(medicalRecords.getFirstName()) &&
                        model.getLastName().equals(medicalRecords.getLastName())).findFirst();
                if (medicalRecordsOptionalToUpdate.isPresent()) {
                	loadJsonData().getMedicalrecords().set(loadJsonData().getMedicalrecords().indexOf(medicalRecordsOptionalToUpdate.get()), model);
                    return model;
                }
            } else loadJsonData().setMedicalrecords(new ArrayList<>());
        }
        return null;
    }

	
	@Override
    public boolean deleteMedicalRecords(MedicalRecords model) {
        if (model != null) {
            if (loadJsonData().getMedicalrecords() != null) {
                Optional<MedicalRecords> medicalRecordsOptionalToUpdate = loadJsonData().getMedicalrecords().stream().filter(medicalRecords -> model.getFirstName().equals(medicalRecords.getFirstName()) &&
                        model.getLastName().equals(medicalRecords.getLastName())).findFirst();
                if (medicalRecordsOptionalToUpdate.isPresent()) {
                	loadJsonData().getMedicalrecords().remove(medicalRecordsOptionalToUpdate.get());
                    return true;
                }
            } else loadJsonData().setMedicalrecords(new ArrayList<>());
        }
        return false;
    }
}