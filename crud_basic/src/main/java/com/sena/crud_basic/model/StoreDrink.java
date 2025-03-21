package com.sena.crud_basic.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity(name="store_drink")
public class StoreDrink {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="store_drink_id")
    private int store_drink_id;

    @ManyToOne
    @JoinColumn(name="store_id", nullable = false)
    private Store store;

    @ManyToOne
    @JoinColumn(name="drink_id", nullable = false)
    private Drink drink;

    @Column(name="stock", nullable = false)
    private int stock;

    

    public StoreDrink() {
    }

    public StoreDrink(int store_drink_id, Store store, Drink drink, int stock) {
        this.store_drink_id = store_drink_id;
        this.store = store;
        this.drink = drink;
        this.stock = stock;
    }

    public int getStore_drink_id() {
        return store_drink_id;
    }

    public void setStore_drink_id(int store_drink_id) {
        this.store_drink_id = store_drink_id;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
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
