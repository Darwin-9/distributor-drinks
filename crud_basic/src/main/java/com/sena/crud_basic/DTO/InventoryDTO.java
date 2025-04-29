package com.sena.crud_basic.DTO;

import java.time.LocalDateTime;

public class InventoryDTO {

    private int drink_id;
    private int current_stock;
    private LocalDateTime last_update;
    private boolean status;
    

    public InventoryDTO(int drink_id, int current_stock, LocalDateTime last_update, boolean status) {
        this.drink_id = drink_id;
        this.current_stock = current_stock;
        this.last_update = last_update;
        this.status = status;
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
    public boolean getStatus() {
        return status;
    }
    public void setStatus(boolean status) {
        this.status = status;
    }
    public int getDrink_id() {
        return drink_id;
    }
    public void setDrink_id(int drink_id) {
        this.drink_id = drink_id;
    }
}
