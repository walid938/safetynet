package com.safetynets.alerts.model;

import com.fasterxml.jackson.annotation.JsonView;
import com.safetynets.alerts.util.View;

import java.util.List;
import java.util.Objects;

@JsonView(View.FloodStations.class)
public class StationInfo {
    private int station;
    private List<AddressInfo> listInfo;

    public StationInfo(List<AddressInfo> listInfoByAddresses, int station) {
        this.listInfo = listInfoByAddresses;
        this.station = station;
    }

    public List<AddressInfo> getListInfo() {
        return listInfo;
    }

    public void setListInfo(List<AddressInfo> listInfo) {
        this.listInfo = listInfo;
    }

    public int getStation() {
        return station;
    }

    public void setStation(int station) {
        this.station = station;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StationInfo that = (StationInfo) o;
        return station == that.station &&
                Objects.equals(listInfo, that.listInfo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(station, listInfo);
    }

    @Override
    public String toString() {
        return "StationInfo{" +
                "AddressInfo=" + listInfo +
                ", station=" + station +
                '}';
    }
}