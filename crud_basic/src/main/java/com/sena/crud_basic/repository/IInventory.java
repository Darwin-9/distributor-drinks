package com.sena.crud_basic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

import com.sena.crud_basic.model.Inventory;

public interface IInventory extends JpaRepository<Inventory, Integer> {


    @Query("SELECT i FROM inventory i WHERE " +
       "(:last_update IS NULL OR CAST(i.last_update AS string) LIKE %:last_update%) AND " +
       "(:current_stock IS NULL OR i.current_stock = :current_stock)")
List<Inventory> filterInventory(@Param("last_update") String last_update,
                                @Param("current_stock") Integer current_stock);

    /*
     * C
     * R
     * U
     * D
     */
}
