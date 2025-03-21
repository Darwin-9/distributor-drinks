package com.sena.crud_basic.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity(name="drink")
public class Drink {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="drink_id")
    private int drink_id;

    @Column(name="drink_name", length=50, nullable = false)
    private String drink_name;

    @Column(name="volume", nullable = false)
    private int volume;

    @Column(name="price", nullable = false)
    private double price;

    @ManyToOne
    @JoinColumn(name= "trademark_id")
    private Trademark trademark;

    

    public Drink() {
    }

    public Drink(int drink_id, String drink_name, int volume, double price, Trademark trademark) {
        this.drink_id = drink_id;
        this.drink_name = drink_name;
        this.volume = volume;
        this.price = price;
        this.trademark = trademark;
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

    public Trademark getTrademark() {
        return trademark;
    }

    public void setTrademark(Trademark trademark) {
        this.trademark = trademark;
    }
}
