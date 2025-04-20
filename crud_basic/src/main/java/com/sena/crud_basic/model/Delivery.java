package com.sena.crud_basic.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.ManyToOne;

import java.time.LocalDateTime;

@Entity(name="delivery")
public class Delivery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="delivery_id")
    private int delivery_id;

    @OneToOne
    @JoinColumn(name="order_id", nullable=false, unique=true)
    private Order order;

    @ManyToOne
    @JoinColumn(name="truck_id", nullable=false)
    private Truck truck;

    @ManyToOne
    @JoinColumn(name="driver_id", nullable=false)
    private Driver driver;

    @Column(name="delivery_date", nullable=false)
    private LocalDateTime delivery_date;

    @Column(name="status", nullable=false, columnDefinition = "boolean default true")
    private boolean status;

    public Delivery() {
    }

    public Delivery(int delivery_id, Order order, Truck truck, Driver driver, LocalDateTime delivery_date, boolean status) {
        this.delivery_id = delivery_id;
        this.order = order;
        this.truck = truck;
        this.driver = driver;
        this.delivery_date = delivery_date;
        this.status = status;
    }

    public int getDelivery_id() {
        return delivery_id;
    }

    public void setDelivery_id(int delivery_id) {
        this.delivery_id = delivery_id;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Truck getTruck() {
        return truck;
    }

    public void setTruck(Truck truck) {
        this.truck = truck;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public LocalDateTime getDelivery_date() {
        return delivery_date;
    }

    public void setDelivery_date(LocalDateTime delivery_date) {
        this.delivery_date = delivery_date;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}