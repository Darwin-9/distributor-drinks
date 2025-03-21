package com.sena.crud_basic.DTO;

import com.sena.crud_basic.model.Drink;
import com.sena.crud_basic.model.Store;

public class StoreDrinkDTO {

    private Store store;
    private Drink drink;
    private int stock;

    public StoreDrinkDTO(Store store, Drink drink, int stock) {
        this.store = store;
        this.drink = drink;
        this.stock = stock;
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

    public void setDrink_id(Drink drink) {
        this.drink = drink;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}
