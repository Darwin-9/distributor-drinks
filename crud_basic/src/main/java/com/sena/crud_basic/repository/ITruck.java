package com.sena.crud_basic.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import com.sena.crud_basic.model.Truck;

public interface ITruck extends JpaRepository<Truck, Integer> {

    @Query("SELECT t FROM truck t WHERE" +
        "(:id IS NULL OR t.truck_id = :id) AND" +
        "(:model IS NULL OR t.model LIKE CONCAT('%', :model, '%')) AND" +
        "(:capacity IS NULL OR t.capacity = :capacity) AND" +
        "(:plateNumber IS NULL OR t.plate_number LIKE CONCAT('%', :plateNumber, '%'))")
List<Truck> filterTrucks(@Param("id") Integer id,
                         @Param("model") String model,
                         @Param("capacity") Double capacity,
                         @Param("plateNumber") String plateNumber);

                            
    /*
     * C
     * R
     * U
     * D
     */
}
