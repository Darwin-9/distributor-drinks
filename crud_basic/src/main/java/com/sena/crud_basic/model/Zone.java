package com.sena.crud_basic.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity(name="zone")
public class Zone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="zone_id")
    private int zone_id;

    @Column(name="zone_name", length=50, nullable = false)
    private String zone_name;

    @Column(name="city", length=50, nullable = false)
    private String city;


    public Zone() {
    }

    public Zone(int zone_id, String zone_name, String city) {
        this.zone_id = zone_id;
        this.zone_name = zone_name;
        this.city = city;
    }

    public int getZone_id() {
        return zone_id;
    }

    public void setZone_id(int zone_id) {
        this.zone_id = zone_id;
    }

    public String getZone_name() {
        return zone_name;
    }

    public void setZone_name(String zone_name) {
        this.zone_name = zone_name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
