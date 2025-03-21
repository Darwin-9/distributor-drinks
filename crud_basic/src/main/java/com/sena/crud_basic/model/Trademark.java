package com.sena.crud_basic.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity(name="trademark")
public class Trademark {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="trademark_id")
    private int trademark_id;

    @Column(name="trademark_name", length=50, nullable = false)
    private String trademark_name;

    

    public Trademark() {
    }

    public Trademark(int trademark_id, String trademark_name) {
        this.trademark_id = trademark_id;
        this.trademark_name = trademark_name;
    }

    public int getTrademark_id() {
        return trademark_id;
    }

    public void setTrademark_id(int trademark_id) {
        this.trademark_id = trademark_id;
    }

    public String getTrademark_name() {
        return trademark_name;
    }

    public void setTrademark_name(String trademark_name) {
        this.trademark_name = trademark_name;
    }
}
