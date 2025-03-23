package com.sena.crud_basic.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity(name="store_user")
public class StoreUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="store_user_id")
    private int store_user_id;

    @Column(name="username", nullable=false, length=50, unique=true)
    private String username;

    @Column(name="password", nullable=false, length=255)
    private String password;

    @Column(name="email", nullable=false, length=100, unique=true)
    private String email;

    @OneToOne
    @JoinColumn(name="store_id", nullable=false, unique=true)
    private Store store;

    public StoreUser() {
    }

    public StoreUser(int store_user_id, String username, String password, String email, Store store) {
        this.store_user_id = store_user_id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.store = store;
    }

    public int getStore_user_id() {
        return store_user_id;
    }

    public void setStore_user_id(int store_user_id) {
        this.store_user_id = store_user_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }
}
