package com.sena.crud_basic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

import com.sena.crud_basic.model.Order;

public interface IOrder extends JpaRepository<Order, Integer> {

    @Query("SELECT o FROM order_info o WHERE " +
        "(:order_date IS NULL OR CAST(o.order_date AS string) LIKE %:order_date%) AND " +
        "(:status IS NULL OR o.status = :status)")
List<Order> filterOrders(@Param("order_date") String order_date,
                         @Param("status") Boolean status);

    /*
     * C
     * R
     * U
     * D
     */
}
