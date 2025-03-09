package com.sena.crud_basic.model;

import jakarta.persistence.*;

@Entity(name="store_drink")
public class StoreDrink {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="store_drink_id", nullable = false)
    private int store_drink_id;

    @Column(name="store_id", nullable = false)
    private int store_id;

    @Column(name="drink_id", nullable = false)
    private int drink_id;

    @Column(name="stock", nullable = false)
    private int stock;

    public StoreDrink(int store_drink_id, int store_id, int drink_id, int stock) {
        this.store_drink_id = store_drink_id;
        this.store_id = store_id;
        this.drink_id = drink_id;
        this.stock = stock;
    }

    public int getStore_drink_id() {
        return store_drink_id;
    }

    public void setStore_drink_id(int store_drink_id) {
        this.store_drink_id = store_drink_id;
    }

    public int getStore_id() {
        return store_id;
    }

    public void setStore_id(int store_id) {
        this.store_id = store_id;
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
