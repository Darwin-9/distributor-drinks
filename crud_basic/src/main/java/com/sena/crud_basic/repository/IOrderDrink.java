package com.sena.crud_basic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sena.crud_basic.model.OrderDrink;
import java.util.List;

public interface IOrderDrink extends JpaRepository<OrderDrink, Integer> {

    // En IOrderDrink.java (extiende JpaRepository)
@Query("SELECT od FROM order_drink od WHERE " +
       "(:quantity IS NULL OR od.quantity = :quantity) AND " +
       "(:drinkId IS NULL OR od.drink.id = :drinkId) AND " +  // Asume relación con Drink
       "(:orderId IS NULL OR od.order.id = :orderId)")       // Asume relación con Order
List<OrderDrink> filterOrderDrinks(
    @Param("quantity") Integer quantity,
    @Param("drinkId") Integer drinkId,
    @Param("orderId") Integer orderId
);
    /*
     * C
     * R
     * U
     * D
     */
}
