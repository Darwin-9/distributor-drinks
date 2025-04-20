package com.sena.crud_basic.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.time.LocalDateTime;

@Entity(name="order_info")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="order_id")
    private int order_id;

    @ManyToOne
    @JoinColumn(name="store_id", nullable=false)
    private Store store;

    @Column(name="order_date", nullable=false)
    private LocalDateTime order_date;

    @Column(name="status", nullable=false, columnDefinition = "boolean default true ")
    private boolean status;


    public Order() {
    }

    public Order(int order_id, Store store, LocalDateTime order_date, boolean status) {
        this.order_id = order_id;
        this.store = store;
        this.order_date = order_date;
        this.status = status;
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public LocalDateTime getOrder_date() {
        return order_date;
    }

    public void setOrder_date(LocalDateTime order_date) {
        this.order_date = order_date;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
