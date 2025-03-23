package com.sena.crud_basic.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity(name="truck")
public class Truck {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="truck_id")
    private int truck_id;

    @Column(name="capacity", nullable=false)
    private double capacity;

    @Column(name="model", nullable=false, length=50)
    private String model;

    @Column(name="plate_number", nullable=false, length=20, unique=true)
    private String plate_number;

    public Truck() {
    }

    public Truck(int truck_id, double capacity, String model, String plate_number) {
        this.truck_id = truck_id;
        this.capacity = capacity;
        this.model = model;
        this.plate_number = plate_number;
    }

    public int getTruck_id() {
        return truck_id;
    }

    public void setTruck_id(int truck_id) {
        this.truck_id = truck_id;
    }

    public double getCapacity() {
        return capacity;
    }

    public void setCapacity(double capacity) {
        this.capacity = capacity;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getPlate_number() {
        return plate_number;
    }

    public void setPlate_number(String plate_number) {
        this.plate_number = plate_number;
    }
}
