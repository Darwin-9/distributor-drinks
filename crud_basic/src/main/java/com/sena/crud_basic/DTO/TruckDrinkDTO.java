package com.sena.crud_basic.DTO;

public class TruckDrinkDTO {

    private int truck_id;
    private int drink_id;
    private int stock;

    public TruckDrinkDTO(int truck_id, int drink_id, int stock) {
        this.truck_id = truck_id;
        this.drink_id = drink_id;
        this.stock = stock;
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
