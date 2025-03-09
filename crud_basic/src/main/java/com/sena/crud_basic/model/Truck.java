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
    @Column(name="truck_id", nullable = false)
    private int truck_id;

    @Column(name="plate_number", length=20, nullable = false)
    private String plate_number;

    @Column(name="model", length=50, nullable = false)
    private String model;

    @Column(name="capacity", nullable = false)
    private int capacity;

    public Truck(int truck_id, String plate_number, String model, int capacity) {
        this.truck_id = truck_id;
        this.plate_number = plate_number;
        this.model = model;
        this.capacity = capacity;
    }

    public int getTruck_id() {
        return truck_id;
    }

    public void setTruck_id(int truck_id) {
        this.truck_id = truck_id;
    }

    public String getPlate_number() {
        return plate_number;
    }

    public void setPlate_number(String plate_number) {
        this.plate_number = plate_number;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
}
