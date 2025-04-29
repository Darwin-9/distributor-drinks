package com.sena.crud_basic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;


import com.sena.crud_basic.model.StoreUser;

public interface IStoreUser extends JpaRepository<StoreUser, Integer> {


    @Query("SELECT su FROM store_user su WHERE " +
    "LOWER(su.username) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR " +
    "LOWER(su.email) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR " +
    "LOWER(su.store.name) LIKE LOWER(CONCAT('%', :searchTerm, '%'))")
List<StoreUser> filterStoreUsers(@Param("searchTerm") String searchTerm);

    /*
     * C
     * R
     * U
     * D
     */
}
