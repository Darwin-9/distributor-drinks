package com.sena.crud_basic.DTO;

public class ZoneDTO {

    private String zone_name;
    private String city;

    public ZoneDTO(String zone_name, String city) {
        this.zone_name = zone_name;
        this.city = city;
    }

    public String getZone_name() {
        return zone_name;
    }

    public void setZone_name(String zone_name) {
        this.zone_name = zone_name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
