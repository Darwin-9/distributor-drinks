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
    @Column(name="drink_id", nullable = false)
    private int drink_id;

    @Column(name="drink_name", length=50, nullable = false)
    private String drink_name;

    @Column(name="volume", nullable = false)
    private int volume;

    @Column(name="price", nullable = false)
    private double price;

    @Column(name="trademark_id", nullable = false)
    private int trademark_id;

    public Drink(int drink_id, String drink_name, int volume, double price, int trademark_id) {
        this.drink_id = drink_id;
        this.drink_name = drink_name;
        this.volume = volume;
        this.price = price;
        this.trademark_id = trademark_id;
    }

    public int getDrink_id() {
        return drink_id;
    }

    public void setDrink_id(int drink_id) {
        this.drink_id = drink_id;
    }

    public String getDrink_name() {
        return drink_name;
    }

    public void setDrink_name(String drink_name) {
        this.drink_name = drink_name;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getTrademark_id() {
        return trademark_id;
    }

    public void setTrademark_id(int trademark_id) {
        this.trademark_id = trademark_id;
    }
}
