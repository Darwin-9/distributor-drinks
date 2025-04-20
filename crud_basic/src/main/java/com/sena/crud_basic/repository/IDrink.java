package com.sena.crud_basic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sena.crud_basic.model.Drink;
import java.util.List;


public interface IDrink extends JpaRepository<Drink, Integer> {

    @Query("SELECT d FROM drink d WHERE" +
            "(:id IS NULL OR d.drink_id = :id) AND" +
            "(:name IS NULL OR d.name LIKE CONCAT('%', :name, '%')) AND" +
            "(:price IS NULL OR d.price = :price) AND" +
            "(:volume IS NULL OR d.volume = :volume) AND" +
            "(:stock IS NULL OR d.stock = :stock)")
    List<Drink> filterDrinks(@Param("id") Integer id,
                             @Param("name") String name,
                             @Param("price") Double price,
                             @Param("volume") Double volume,
                             @Param("stock") Integer stock);
                            
    

    /*
     * C
     * R
     * U
     * D
     */
}
