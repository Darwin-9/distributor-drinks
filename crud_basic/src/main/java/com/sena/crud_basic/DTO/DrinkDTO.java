package com.sena.crud_basic.DTO;

public class DrinkDTO {

    private String name;
    private double price;
    private double volume; 
    private int stock;

    public DrinkDTO(String name, double price, double volume, int stock) {
        this.name = name;
        this.price = price;
        this.volume = volume;
        this.stock = stock;
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
