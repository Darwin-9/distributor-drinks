package com.sena.crud_basic.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity(name="driver")
public class Driver {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="driver_id", nullable = false)
    private int driver_id;

    @Column(name="driver_first_name", length=50, nullable = false)
    private String driver_first_name;

    @Column(name="driver_last_name", length=50, nullable = false)
    private String driver_last_name;

    @Column(name="license_number", length=20, nullable = false)
    private String license_number;

    public Driver(int driver_id, String driver_first_name, String driver_last_name, String license_number) {
        this.driver_id = driver_id;
        this.driver_first_name = driver_first_name;
        this.driver_last_name = driver_last_name;
        this.license_number = license_number;
    }

    public int getDriver_id() {
        return driver_id;
    }

    public void setDriver_id(int driver_id) {
        this.driver_id = driver_id;
    }

    public String getDriver_first_name() {
        return driver_first_name;
    }

    public void setDriver_first_name(String driver_first_name) {
        this.driver_first_name = driver_first_name;
    }

    public String getDriver_last_name() {
        return driver_last_name;
    }

    public void setDriver_last_name(String driver_last_name) {
        this.driver_last_name = driver_last_name;
    }

    public String getLicense_number() {
        return license_number;
    }

    public void setLicense_number(String license_number) {
        this.license_number = license_number;
    }
}
