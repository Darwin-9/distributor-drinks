package com.sena.crud_basic.DTO;

import java.time.LocalDateTime;

public class DeliveryDTO {

    private LocalDateTime delivery_date;

    public DeliveryDTO(LocalDateTime delivery_date) {
        this.delivery_date = delivery_date;
    }

    public LocalDateTime getDelivery_date() {
        return delivery_date;
    }

    public void setDelivery_date(LocalDateTime delivery_date) {
        this.delivery_date = delivery_date;
    }
}
