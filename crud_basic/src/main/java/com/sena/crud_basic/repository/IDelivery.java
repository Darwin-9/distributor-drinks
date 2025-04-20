package com.sena.crud_basic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

import com.sena.crud_basic.model.Delivery;

public interface IDelivery extends JpaRepository<Delivery, Integer> {

    
    // Filtro para entregas (por fecha y estado)
    @Query("SELECT d FROM delivery d WHERE " +
           "(:deliveryDate IS NULL OR d.delivery_date = :deliveryDate) AND " +
           "(:status IS NULL OR d.status = :status)")
    List<Delivery> filterDeliveries(
            @Param("deliveryDate") LocalDate deliveryDate,  // Asegúrate de importar java.time.LocalDate
            @Param("status") Boolean status
    );
    /*
     * C
     * R
     * U
     * D
     */
}
