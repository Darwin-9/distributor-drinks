package com.sena.crud_basic.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity(name="admin")
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="admin_id")
    private int admin_id;

    @Column(name="username", nullable=false, length=50, unique=true)
    private String username;

    @Column(name="password", nullable=false, length=150)
    private String password;

    @Column(name="email", nullable=false, length=100, unique=true)
    private String email;

    @Column(name = "status", nullable = false, columnDefinition = "boolean default true ")
    private boolean status;

    public Admin() {
    }

    public Admin(int admin_id, String username, String password, String email, boolean status) {
        this.admin_id = admin_id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.status = status;
    }

    public int getAdmin_id() {
        return admin_id;
    }

    public void setAdmin_id(int admin_id) {
        this.admin_id = admin_id;
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

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    
}
