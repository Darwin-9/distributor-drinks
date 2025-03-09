package com.sena.crud_basic.DTO;

public class TruckDTO {

    private String plate_number;
    private String model;
    private int capacity;

    public TruckDTO(String plate_number, String model, int capacity) {
        this.plate_number = plate_number;
        this.model = model;
        this.capacity = capacity;
    }

    public String getPlate_number() {
        return plate_number;
    }

    public void setPlate_number(String plate_number) {
        this.plate_number = plate_number;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
}
