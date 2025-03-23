package com.sena.crud_basic.DTO;

public class TruckDTO {

    private double capacity;
    private String model;
    private String plate_number;

    public TruckDTO(double capacity, String model, String plate_number) {
        this.capacity = capacity;
        this.model = model;
        this.plate_number = plate_number;
    }

    public double getCapacity() {
        return capacity;
    }

    public void setCapacity(double capacity) {
        this.capacity = capacity;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getPlate_number() {
        return plate_number;
    }

    public void setPlate_number(String plate_number) {
        this.plate_number = plate_number;
    }
}
