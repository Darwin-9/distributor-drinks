package com.sena.crud_basic.DTO;

public class DistributorDTO {

    private String distributor_name;
    private String address;
    private String phone_number;

    public DistributorDTO(String distributor_name, String address, String phone_number) {
        this.distributor_name = distributor_name;
        this.address = address;
        this.phone_number = phone_number;
    }

    public String getDistributor_name() {
        return distributor_name;
    }

    public void setDistributor_name(String distributor_name) {
        this.distributor_name = distributor_name;
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
