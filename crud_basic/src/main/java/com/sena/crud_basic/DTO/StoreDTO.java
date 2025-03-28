package com.sena.crud_basic.DTO;

public class StoreDTO {

    private String name;
    private String address;
    private String phone_number;
    private String city;

    public StoreDTO(String name, String address, String phone_number, String city) {
        this.name = name;
        this.address = address;
        this.phone_number = phone_number;
        this.city = city;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
