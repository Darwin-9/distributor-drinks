package com.sena.crud_basic.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity(name="distributor")
public class Distributor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="distributor_id")
    private int distributor_id;

    @Column(name="distributor_name", length=50, nullable = false)
    private String distributor_name;

    @Column(name="address", length=50, nullable = false)
    private String address;

    @Column(name="phone_number", length=15, nullable = false)
    private String phone_number;

    // Constructor vacío
    public Distributor() {}

    // Constructor con parámetros
    public Distributor(int distributor_id, String distributor_name, String address, String phone_number) {
        this.distributor_id = distributor_id;
        this.distributor_name = distributor_name;
        this.address = address;
        this.phone_number = phone_number;
    }

    // Getters y Setters
    public int getDistributor_id() {
        return distributor_id;
    }

    public void setDistributor_id(int distributor_id) {
        this.distributor_id = distributor_id;
    }

    public String getDistributor_name() {
        return distributor_name;
    }

    public void setDistributor_name(String distributor_name) {
        this.distributor_name = distributor_name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }
}
