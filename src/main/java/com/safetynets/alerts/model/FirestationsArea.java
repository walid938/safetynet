package com.safetynets.alerts.model;

import com.fasterxml.jackson.annotation.JsonView;
import com.safetynets.alerts.model.Person;
import com.safetynets.alerts.util.View;

import java.util.List;
import java.util.Objects;

@JsonView(View.FirestationById.class)
public class FirestationsArea {

    private List<Person> persons;
    private long adults;
    private long children;

    public FirestationsArea(List<Person> persons, long adults, long children) {
        this.persons = persons;
        this.adults = adults;
        this.children = children;
    }

    public List<Person> getPersons() {
        return persons;
    }

    public void setPersons(List<Person> persons) {
        this.persons = persons;
    }

    public long getAdults() {
        return adults;
    }

    public void setAdults(int adults) {
        this.adults = adults;
    }

    public long getChildren() {
        return children;
    }

    public void setChildren(int children) {
        this.children = children;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FirestationsArea that = (FirestationsArea) o;
        return adults == that.adults &&
                children == that.children &&
                Objects.equals(persons, that.persons);
    }

    @Override
    public int hashCode() {
        return Objects.hash(persons, adults, children);
    }

    @Override
    public String toString() {
        return "FirestationsArea{" +
                "persons=" + persons +
                ", adults=" + adults +
                ", children=" + children +
                '}';
    }
}