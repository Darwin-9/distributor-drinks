package com.sena.crud_basic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

import com.sena.crud_basic.model.Store;

public interface IStore extends JpaRepository<Store, Integer> {

    @Query("SELECT s FROM store s WHERE" +
       "(:id IS NULL OR s.store_id = :id) AND" +
       "(:name IS NULL OR s.name LIKE CONCAT('%', :name, '%')) AND" +
       "(:address IS NULL OR s.address LIKE CONCAT('%', :address, '%')) AND" +
       "(:phone_number IS NULL OR s.phone_number LIKE CONCAT('%', :phone_number, '%')) AND" +
       "(:city IS NULL OR s.city LIKE CONCAT('%', :city, '%'))")
List<Store> filterStores(@Param("id") Integer id,
                         @Param("name") String name,
                         @Param("address") String address,
                         @Param("phone_number") String phone_number,
                         @Param("city") String city);

    /*
     * C
     * R
     * U
     * D
     */
}
