package com.sena.crud_basic.model;

import jakarta.persistence.*;

@Entity(name="truck_drink")
public class TruckDrink {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="truck_drink_id", nullable = false)
    private int truck_drink_id;

    @Column(name="truck_id", nullable = false)
    private int truck_id;

    @Column(name="drink_id", nullable = false)
    private int drink_id;

    @Column(name="stock", nullable = false)
    private int stock;

    public TruckDrink(int truck_drink_id, int truck_id, int drink_id, int stock) {
        this.truck_drink_id = truck_drink_id;
        this.truck_id = truck_id;
        this.drink_id = drink_id;
        this.stock = stock;
    }

    public int getTruck_drink_id() {
        return truck_drink_id;
    }

    public void setTruck_drink_id(int truck_drink_id) {
        this.truck_drink_id = truck_drink_id;
    }

    public int getTruck_id() {
        return truck_id;
    }

    public void setTruck_id(int truck_id) {
        this.truck_id = truck_id;
    }

    public int getDrink_id() {
        return drink_id;
    }

    public void setDrink_id(int drink_id) {
        this.drink_id = drink_id;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}
