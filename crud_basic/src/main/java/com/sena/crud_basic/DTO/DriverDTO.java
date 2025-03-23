package com.sena.crud_basic.DTO;

public class DriverDTO {

    private String first_name;
    private String last_name;
    private String license_number;
    private int truck_id;

    public DriverDTO(String first_name, String last_name, String license_number, int truck_id) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.license_number = license_number;
        this.truck_id = truck_id;
        
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getLicense_number() {
        return license_number;
    }

    public void setLicense_number(String license_number) {
        this.license_number = license_number;
    }

    public int getTruck_id() {
        return truck_id;
    }

    public void setTruck_id(int truck_id) {
        this.truck_id = truck_id;
    }

    
}

