package com.sena.crud_basic.DTO;

public class OrderDrinkDTO {

    private int order_id;
    private int drink_id;
    private int quantity;

    public OrderDrinkDTO(int order_id, int drink_id, int quantity) {
        this.order_id = order_id;
        this.drink_id = drink_id;
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public int getOrder_id() {
        return order_id;
    }
    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }
    public int getDrink_id() {
        return drink_id;
    }
    public void setDrink_id(int drink_id) {
        this.drink_id = drink_id;
    }
}
