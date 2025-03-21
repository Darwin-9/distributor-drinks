package com.sena.crud_basic.DTO;

import com.sena.crud_basic.model.Drink;
import com.sena.crud_basic.model.Truck;

public class TruckDrinkDTO {

    private Truck truck;
    private Drink drink;
    private int stock;

    public TruckDrinkDTO(Truck truck, Drink drink, int stock) {
        this.truck = truck;
        this.drink = drink;
        this.stock = stock;
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
