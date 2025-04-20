package com.sena.crud_basic.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sena.crud_basic.model.Driver;

public interface IDriver extends JpaRepository<Driver, Integer> {

    // En IDriver.java (extiende JpaRepository)
@Query("SELECT d FROM driver d WHERE " +
       "(:firstName IS NULL OR LOWER(d.first_name) LIKE LOWER(CONCAT('%', :firstName, '%'))) AND " +
       "(:lastName IS NULL OR LOWER(d.last_name) LIKE LOWER(CONCAT('%', :lastName, '%'))) AND " +
       "(:licenseNumber IS NULL OR LOWER(d.license_number) LIKE LOWER(CONCAT('%', :licenseNumber, '%'))) AND " +
       "(:status IS NULL OR d.status = :status)")
List<Driver> filterDrivers(
    @Param("firstName") String firstName,
    @Param("lastName") String lastName,
    @Param("licenseNumber") String licenseNumber,
    @Param("status") Boolean status
);
    /*
     * C
     * R
     * U
     * D
     */
}
