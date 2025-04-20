package com.sena.crud_basic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

import com.sena.crud_basic.model.StoreUser;

public interface IStoreUser extends JpaRepository<StoreUser, Integer> {


    @Query("SELECT s FROM store_user s WHERE " +
        "(:username IS NULL OR LOWER(s.username) LIKE LOWER(CONCAT('%', :username, '%'))) AND " +
        "(:email IS NULL OR LOWER(s.email) LIKE LOWER(CONCAT('%', :email, '%'))) AND " +
        "(:status IS NULL OR s.status = :status)")
List<StoreUser> filterStoreUsers(@Param("username") String username,
                                 @Param("email") String email,
                                 @Param("status") Boolean status);


    /*
     * C
     * R
     * U
     * D
     */
}
