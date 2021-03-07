package com.safetynets.alerts.model;

import com.fasterxml.jackson.annotation.JsonView;
import com.safetynets.alerts.util.View;

import java.util.List;
import java.util.Objects;

@JsonView(View.Fire.class)
public class Fire {

    private List<Integer> stationNumber;
    private List<PersonInfo> person;

    public Fire(List<Integer> stationNumber, List<PersonInfo> person) {
        this.stationNumber = stationNumber;
        this.person = person;
    }

    public List<Integer> getStationNumber() {
        return stationNumber;
    }

    public void setStationNumber(List<Integer> stationNumber) {
        this.stationNumber = stationNumber;
    }

    public List<PersonInfo> getPerson() {
        return person;
    }

    public void setPerson(List<PersonInfo> person) {
        this.person = person;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Fire that = (Fire) o;
        return Objects.equals(stationNumber, that.stationNumber) &&
                Objects.equals(person, that.person);
    }

    @Override
    public int hashCode() {
        return Objects.hash(stationNumber, person);
    }

    @Override
    public String toString() {
        return "Fire{" +
                "stationNumber=" + stationNumber +
                ", person=" + person +
                '}';
    }
}