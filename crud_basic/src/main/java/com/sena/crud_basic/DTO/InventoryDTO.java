package com.sena.crud_basic.DTO;

import java.time.LocalDateTime;

public class InventoryDTO {

    private int current_stock;
    private LocalDateTime last_update;

    public InventoryDTO(int current_stock, LocalDateTime last_update) {
        this.current_stock = current_stock;
        this.last_update = last_update;
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
