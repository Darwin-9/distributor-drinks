package com.sena.crud_basic.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity(name="driver")
public class Driver {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="driver_id")
    private int driver_id;

    @Column(name="first_name", nullable=false, length=50)
    private String first_name;

    @Column(name="last_name", nullable=false, length=50)
    private String last_name;

    @Column(name="license_number", nullable=false, length=20, unique=true)
    private String license_number;

    @ManyToOne
    @JoinColumn(name="truck_id", nullable=false)
    private Truck truck;

    @Column(name = "status", nullable = false, columnDefinition = "boolean default true ")
    private boolean status;

    public Driver() {
    }

    public Driver(int driver_id, String first_name, String last_name, String license_number, Truck truck, boolean status) {
        this.driver_id = driver_id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.license_number = license_number;
        this.truck = truck;
        this.status = status;
    }

    public int getDriver_id() {
        return driver_id;
    }

    public void setDriver_id(int driver_id) {
        this.driver_id = driver_id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getLicense_number() {
        return license_number;
    }

    public void setLicense_number(String license_number) {
        this.license_number = license_number;
    }

    public Truck getTruck() {
        return truck;
    }

    public void setTruck(Truck truck) {
        this.truck = truck;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    
}
