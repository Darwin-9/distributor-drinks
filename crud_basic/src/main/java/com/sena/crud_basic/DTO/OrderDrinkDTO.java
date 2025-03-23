package com.sena.crud_basic.DTO;

public class OrderDrinkDTO {

    private int quantity;

    public OrderDrinkDTO(int quantity) {
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
