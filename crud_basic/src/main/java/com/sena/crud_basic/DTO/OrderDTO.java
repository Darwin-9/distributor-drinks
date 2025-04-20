package com.sena.crud_basic.DTO;

import java.time.LocalDateTime;

public class OrderDTO {

    private LocalDateTime order_date;
    private boolean status;

    public OrderDTO(LocalDateTime order_date, boolean status) {
        this.order_date = order_date;
        this.status = status;
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
