package com.sena.crud_basic.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity(name="store")
public class Store {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="store_id")
    private int store_id;

    @Column(name="name", nullable=false, length=50)
    private String name;

    @Column(name="address", nullable=false, length=100)
    private String address;

    @Column(name="phone_number", nullable=false, length=15)
    private String phone_number;

    @Column(name="city", nullable=false, length=50)
    private String city;

    @Column(name = "status", nullable = false, columnDefinition = "boolean default true ")
    private boolean status;

    public Store() {
    }

    public Store(int store_id, String name, String address, String phone_number, String city, boolean status) {
        this.store_id = store_id;
        this.name = name;
        this.address = address;
        this.phone_number = phone_number;
        this.city = city;
        this.status = status;
    }

    public int getStore_id() {
        return store_id;
    }

    public void setStore_id(int store_id) {
        this.store_id = store_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    
}