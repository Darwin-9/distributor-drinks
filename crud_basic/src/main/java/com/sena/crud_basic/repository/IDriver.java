package com.sena.crud_basic.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sena.crud_basic.model.Driver;

public interface IDriver extends JpaRepository<Driver, Integer> {

    @Query("SELECT a FROM driver a WHERE a.status != false")
    List<Driver> getListUserActive();
    /*
     * C
     * R
     * U
     * D
     */
}
