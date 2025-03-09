package com.sena.crud_basic.DTO;

public class DrinkDTO {

    private String drink_name;
    private int volume;
    private double price;
    private int trademark_id;

    public DrinkDTO(String drink_name, int volume, double price, int trademark_id) {
        this.drink_name = drink_name;
        this.volume = volume;
        this.price = price;
        this.trademark_id = trademark_id;
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
