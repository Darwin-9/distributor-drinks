package com.sena.crud_basic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sena.crud_basic.model.Drink;
import java.util.List;


public interface IDrink extends JpaRepository<Drink, Integer> {

        @Query("SELECT d FROM drink d WHERE " +
        "(:search IS NULL OR " +
        "d.name LIKE CONCAT('%', :search, '%') OR " +
        "CAST(d.price AS string) LIKE CONCAT('%', :search, '%') OR " +
        "CAST(d.volume AS string) LIKE CONCAT('%', :search, '%') OR " +
        "CAST(d.stock AS string) LIKE CONCAT('%', :search, '%'))")
 List<Drink> filterDrinks(@Param("search") String search);
                            
    

    /*
     * C
     * R
     * U
     * D
     */
}
