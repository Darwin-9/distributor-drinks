package com.sena.crud_basic.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity(name="order_drink")
public class OrderDrink {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="order_drink_id")
    private int order_drink_id;

    @ManyToOne
    @JoinColumn(name="order_id", nullable=false)
    private Order order;

    @ManyToOne
    @JoinColumn(name="drink_id", nullable=false)
    private Drink drink;

    @Column(name="quantity", nullable=false)
    private int quantity;

    public OrderDrink() {
    }

    public OrderDrink(int order_drink_id, Order order, Drink drink, int quantity) {
        this.order_drink_id = order_drink_id;
        this.order = order;
        this.drink = drink;
        this.quantity = quantity;
    }

    public int getOrder_drink_id() {
        return order_drink_id;
    }

    public void setOrder_drink_id(int order_drink_id) {
        this.order_drink_id = order_drink_id;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Drink getDrink() {
        return drink;
    }

    public void setDrink(Drink drink) {
        this.drink = drink;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
