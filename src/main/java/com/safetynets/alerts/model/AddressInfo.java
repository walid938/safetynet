package com.safetynets.alerts.model;

import com.fasterxml.jackson.annotation.JsonView;
import com.safetynets.alerts.util.View;

import java.util.List;
import java.util.Objects;

@JsonView(View.FloodStations.class)
public class AddressInfo {
    private String address;
    private List<PersonInfo> Persons;

    public AddressInfo(String address, List<PersonInfo> listPerson) {
        this.address = address;
        this.Persons = listPerson;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<PersonInfo> getPersons() {
        return Persons;
    }

    public void setPersons(List<PersonInfo> persons) {
        this.Persons = persons;
    }

    public void addPerson(PersonInfo person) {
        this.Persons.add(person);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AddressInfo that = (AddressInfo) o;
        return Objects.equals(address, that.address) &&
                Objects.equals(Persons, that.Persons);
    }

    @Override
    public int hashCode() {
        return Objects.hash(address, Persons);
    }

    @Override
    public String toString() {
        return "AddressInfo{" +
                ", address='" + address + '\'' +
                ", listPerson=" + Persons +
                '}';
    }
}