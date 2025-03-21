package com.sena.crud_basic.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity(name="store")
public class Store {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="store_id")
    private int store_id;

    @Column(name="store_name", length=50, nullable = false)
    private String store_name;

    @Column(name="address", length=100, nullable = false)
    private String address;

    @Column(name="phone_number", length=15, nullable = false)
    private String phone_number;

    @ManyToOne
    @JoinColumn(name = "zone_id")
    private Zone zone;


    public Store() {
    }

    public Store(int store_id, String store_name, String address, String phone_number, Zone zone){
        this.store_id = store_id;
        this.store_name = store_name;
        this.address = address;
        this.phone_number = phone_number;
        this.zone = zone;
    }

    public int getStore_id() {
        return store_id;
    }

    public void setStore_id(int store_id) {
        this.store_id = store_id;
    }

    public String getStore_name() {
        return store_name;
    }

    public void setStore_name(String store_name) {
        this.store_name = store_name;
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

    public void setZone(Zone zone) {
        this.zone = zone;
    }

    public Zone getZone() {
        return zone;
    }

   

    

   

    
}
