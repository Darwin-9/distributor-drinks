package com.sena.crud_basic.DTO;

import java.time.LocalDateTime;

public class OrderDTO {

    private LocalDateTime order_date;
    private boolean status;
    private int store_id;

    public OrderDTO(LocalDateTime order_date, boolean status, int store_id) {
        this.order_date = order_date;
        this.status = status;
        this.store_id = store_id;
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

    public int getStore_id() {
        return store_id;
    }

    public void setStore_id(int store_id) {
        this.store_id = store_id;
    }
}
