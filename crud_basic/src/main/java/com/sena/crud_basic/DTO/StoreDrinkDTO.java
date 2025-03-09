package com.sena.crud_basic.DTO;

public class StoreDrinkDTO {

    private int store_id;
    private int drink_id;
    private int stock;

    public StoreDrinkDTO(int store_id, int drink_id, int stock) {
        this.store_id = store_id;
        this.drink_id = drink_id;
        this.stock = stock;
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
