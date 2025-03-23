package com.sena.crud_basic.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity(name="drink")
public class Drink {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="drink_id")
    private int drink_id;

    @Column(name="name", nullable=false, length=100)
    private String name;

    @Column(name="price", nullable=false)
    private double price;

    @Column(name="volume", nullable=false)
    private double volume;

    @Column(name="stock", nullable=false)
    private int stock;

    public Drink() {
    }

    public Drink(int drink_id, String name, double price, double volume, int stock) {
        this.drink_id = drink_id;
        this.name = name;
        this.price = price;
        this.volume = volume;
        this.stock = stock;
    }

    public int getDrink_id() {
        return drink_id;
    }

    public void setDrink_id(int drink_id) {
        this.drink_id = drink_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getVolume() {
        return volume;
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}
