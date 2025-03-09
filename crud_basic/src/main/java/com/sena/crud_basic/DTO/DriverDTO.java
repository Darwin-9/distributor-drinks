package com.sena.crud_basic.DTO;

public class DriverDTO {

    private String driver_first_name;
    private String driver_last_name;
    private String license_number;

    public DriverDTO(String driver_first_name, String driver_last_name, String license_number) {
        this.driver_first_name = driver_first_name;
        this.driver_last_name = driver_last_name;
        this.license_number = license_number;
    }

    public String getDriver_first_name() {
        return driver_first_name;
    }

    public void setDriver_first_name(String driver_first_name) {
        this.driver_first_name = driver_first_name;
    }

    public String getDriver_last_name() {
        return driver_last_name;
    }

    public void setDriver_last_name(String driver_last_name) {
        this.driver_last_name = driver_last_name;
    }

    public String getLicense_number() {
        return license_number;
    }

    public void setLicense_number(String license_number) {
        this.license_number = license_number;
    }
}
