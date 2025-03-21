package com.sena.crud_basic.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.time.LocalDateTime;

import com.sena.crud_basic.DTO.DistributorDTO;
import com.sena.crud_basic.DTO.StoreDTO;

@Entity(name="orderr_info")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="order_id")
    private int order_id;

    @ManyToOne
    @JoinColumn(name="store_id")
    private Store store;

    @ManyToOne
    @JoinColumn(name="distributor_id")
    private Distributor distributor;

    @Column(name="order_date")
    private LocalDateTime order_date;

    

    public Order() {
    }

    public Order(int order_id, Store store, Distributor distributor, LocalDateTime order_date) {
        this.order_id = order_id;
        this.store = store;
        this.distributor = distributor;
        this.order_date = order_date;
    }

   

    public Order(int i, StoreDTO store2, DistributorDTO distributor2, LocalDateTime order_date2) {
        //TODO Auto-generated constructor stub
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

    public Distributor getDistributor() {
        return distributor;
    }

    public void setDistributor(Distributor distributor) {
        this.distributor = distributor;
    }

    public LocalDateTime getOrder_date() {
        return order_date;
    }

    public void setOrder_date(LocalDateTime order_date) {
        this.order_date = order_date;
    }
}
