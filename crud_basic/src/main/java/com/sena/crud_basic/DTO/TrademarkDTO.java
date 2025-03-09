package com.sena.crud_basic.DTO;

public class TrademarkDTO {

    private String trademark_name;

    public TrademarkDTO(String trademark_name) {
        this.trademark_name = trademark_name;
    }

    public String getTrademark_name() {
        return trademark_name;
    }

    public void setTrademark_name(String trademark_name) {
        this.trademark_name = trademark_name;
    }
}
