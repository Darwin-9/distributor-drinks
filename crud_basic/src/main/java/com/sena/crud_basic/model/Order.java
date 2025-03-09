package com.sena.crud_basic.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity(name="order")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="order_id", nullable = false)
    private int order_id;

    @Column(name="store_id", nullable = false)
    private int store_id;

    @Column(name="distributor_id", nullable = false)
    private int distributor_id;

    @Column(name="order_date", nullable = false)
    private LocalDateTime order_date;

    public Order(int order_id, int store_id, int distributor_id, LocalDateTime order_date) {
        this.order_id = order_id;
        this.store_id = store_id;
        this.distributor_id = distributor_id;
        this.order_date = order_date;
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public int getStore_id() {
        return store_id;
    }

    public void setStore_id(int store_id) {
        this.store_id = store_id;
    }

    public int getDistributor_id() {
        return distributor_id;
    }

    public void setDistributor_id(int distributor_id) {
        this.distributor_id = distributor_id;
    }

    public LocalDateTime getOrder_date() {
        return order_date;
    }

    public void setOrder_date(LocalDateTime order_date) {
        this.order_date = order_date;
    }
}
