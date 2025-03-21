package com.sena.crud_basic.DTO;

import com.sena.crud_basic.model.Trademark;

public class DrinkDTO {

    private String drink_name;
    private int volume;
    private double price;
    private Trademark trademark;

    public DrinkDTO(String drink_name, int volume, double price, Trademark trademark) {
        this.drink_name = drink_name;
        this.volume = volume;
        this.price = price;
        this.trademark = trademark;
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
