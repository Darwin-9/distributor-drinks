package com.sena.crud_basic.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity(name="truck_drink")
public class TruckDrink {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="truck_drink_id")
    private int truck_drink_id;

    @ManyToOne
    @JoinColumn(name="truck_id", nullable = false)
    private Truck truck;

    @ManyToOne
    @JoinColumn(name="drink_id", nullable = false)
    private Drink drink;

    @Column(name="stock", nullable = false)
    private int stock;


    public TruckDrink() {
    }

    public TruckDrink(int truck_drink_id, Truck truck, Drink drink, int stock) {
        this.truck_drink_id = truck_drink_id;
        this.truck = truck;
        this.drink = drink;
        this.stock = stock;
    }

    public int getTruck_drink_id() {
        return truck_drink_id;
    }

    public void setTruck_drink_id(int truck_drink_id) {
        this.truck_drink_id = truck_drink_id;
    }

    public Truck getTruck() {
        return truck;
    }

    public void setTruck(Truck truck) {
        this.truck = truck;
    }

    public Drink getDrink() {
        return drink;
    }

    public void setDrink(Drink drink) {
        this.drink = drink;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}
