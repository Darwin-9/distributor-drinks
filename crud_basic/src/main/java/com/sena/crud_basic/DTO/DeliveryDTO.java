package com.sena.crud_basic.DTO;

import java.time.LocalDateTime;

public class DeliveryDTO {

    private LocalDateTime delivery_date;
    private int order;
    private int truck;
    private int driver;

    public DeliveryDTO(LocalDateTime delivery_date, int order, int truck, int driver) {
        this.delivery_date = delivery_date;
        this.order = order;
        this.truck = truck;
        this.driver = driver;
    }

    public LocalDateTime getDelivery_date() {
        return delivery_date;
    }

    public void setDelivery_date(LocalDateTime delivery_date) {
        this.delivery_date = delivery_date;
    }

    public int getOrder() {
        return order;
    }
    
    public void setOrder(int order) {
        this.order = order;
    }
    public int getTruck() {
        return truck;
    }
    public void setTruck(int truck) {
        this.truck = truck;
    }
    public int getDriver() {
        return driver;
    }
    public void setDriver(int driver) {
        this.driver = driver;
    }
}
