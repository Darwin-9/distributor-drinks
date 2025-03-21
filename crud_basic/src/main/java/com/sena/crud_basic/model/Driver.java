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

    @Column(name="driver_first_name", length=50, nullable = false)
    private String driver_first_name;

    @Column(name="driver_last_name", length=50, nullable = false)
    private String driver_last_name;

    @Column(name="license_number", length=20, nullable = false)
    private String license_number;

    @ManyToOne
    @JoinColumn(name = "distributor_id")
    private Distributor distributor;

    @ManyToOne
    @JoinColumn(name = "truck_id")
    private Truck truck;

    

    public Driver() {
    }

    public Driver(int driver_id, String driver_first_name, String driver_last_name, String license_number, Truck truck, Distributor distributor) {
        this.driver_id = driver_id;
        this.truck = truck;
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

    public void setDistributor_id(Distributor distributor) {
        this.distributor = distributor;
    }

    public Distributor getDistributor() {
        return distributor;
    }

    public void setTruck(Truck truck) {
        this.truck = truck;
    }

    public Truck getTruck() {
        return truck;
    }

   

    
}
