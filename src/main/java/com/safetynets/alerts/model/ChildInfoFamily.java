package com.safetynets.alerts.model;



import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonView;
import com.safetynets.alerts.util.View;


@JsonView(View.ChildInfo.class)
public class ChildInfoFamily {

    private String address;
    private List<PersonInfo> Child;
    private List<PersonInfo> Adults;

    public ChildInfoFamily(String address, List<PersonInfo> child, List<PersonInfo> adults) {
        this.address = address;
        Child = child;
        Adults = adults;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<PersonInfo> getChild() {
        return Child;
    }

    public void setChild(List<PersonInfo> child) {
        Child = child;
    }

    public List<PersonInfo> getAdults() {
        return Adults;
    }

    public void setAdults(List<PersonInfo> adults) {
        Adults = adults;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChildInfoFamily that = (ChildInfoFamily) o;
        return Objects.equals(address, that.address) &&
                Objects.equals(Child, that.Child) &&
                Objects.equals(Adults, that.Adults);
    }

    @Override
    public int hashCode() {
        return Objects.hash(address, Child, Adults);
    }

    @Override
    public String toString() {
        return "ChildInfo{" +
                "address='" + address + '\'' +
                ", Child=" + Child +
                ", Adults=" + Adults +
                '}';
    }
}