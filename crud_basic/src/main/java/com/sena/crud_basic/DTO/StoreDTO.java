package com.sena.crud_basic.DTO;


public class StoreDTO {

    private String store_name;
    private String address;
    private String phone_number;

    public StoreDTO(String store_name, String address, String phone_number) {
        this.store_name = store_name;
        this.address = address;
        this.phone_number = phone_number;
    }

    public String getStore_name() {
        return store_name;
    }

    public void setStore_name(String store_name) {
        this.store_name = store_name;
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
}
