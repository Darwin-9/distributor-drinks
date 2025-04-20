package com.sena.crud_basic.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sena.crud_basic.model.Admin;

public interface IAdmin extends JpaRepository<Admin, Integer> {

       
    @Query("SELECT a FROM admin a WHERE " +
    "(:username IS NULL OR a.username LIKE CONCAT('%', :username, '%')) AND " +
    "(:email IS NULL OR a.email LIKE CONCAT('%', :email, '%')) AND " +
    "(:status IS NULL OR a.status = :status)")
List<Admin> filterAdmins(@Param("username") String username,
                      @Param("email") String email,
                      @Param("status") Boolean status);


    /*
     * C
     * R
     * U
     * D
     */
}
