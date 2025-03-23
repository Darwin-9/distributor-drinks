package com.sena.crud_basic.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import java.time.LocalDateTime;

@Entity(name="inventory")
public class Inventory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="inventory_id")
    private int inventory_id;

    @OneToOne
    @JoinColumn(name="drink_id", nullable=false, unique=true)
    private Drink drink;

    @Column(name="current_stock", nullable=false)
    private int current_stock;

    @Column(name="last_update", nullable=false)
    private LocalDateTime last_update;

    public Inventory() {
    }

    public Inventory(int inventory_id, Drink drink, int current_stock, LocalDateTime last_update) {
        this.inventory_id = inventory_id;
        this.drink = drink;
        this.current_stock = current_stock;
        this.last_update = last_update;
    }

    public int getInventory_id() {
        return inventory_id;
    }

    public void setInventory_id(int inventory_id) {
        this.inventory_id = inventory_id;
    }

    public Drink getDrink() {
        return drink;
    }

    public void setDrink(Drink drink) {
        this.drink = drink;
    }

    public int getCurrent_stock() {
        return current_stock;
    }

    public void setCurrent_stock(int current_stock) {
        this.current_stock = current_stock;
    }

    public LocalDateTime getLast_update() {
        return last_update;
    }

    public void setLast_update(LocalDateTime last_update) {
        this.last_update = last_update;
    }
}
