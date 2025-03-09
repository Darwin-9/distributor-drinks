package com.sena.crud_basic.DTO;

import java.time.LocalDateTime;

public class OrderDTO {

    private int store_id;
    private int distributor_id;
    private LocalDateTime order_date;

    public OrderDTO(int store_id, int distributor_id, LocalDateTime order_date) {
        this.store_id = store_id;
        this.distributor_id = distributor_id;
        this.order_date = order_date;
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
