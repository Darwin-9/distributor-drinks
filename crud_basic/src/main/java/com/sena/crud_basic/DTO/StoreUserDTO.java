package com.sena.crud_basic.DTO;

public class StoreUserDTO {

    private String username;
    private String email;
    private String password;
    private int store_id;

    public StoreUserDTO(String username, String email, String password, int store_id) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.store_id = store_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getStore_id() {
        return store_id;
    }

    public void setStore_id(int store_id) {
        this.store_id = store_id;
    }
}
