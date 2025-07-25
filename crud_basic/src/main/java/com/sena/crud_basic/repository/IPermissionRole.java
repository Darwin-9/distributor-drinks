package com.sena.crud_basic.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sena.crud_basic.model.PermissionRole;

public interface IPermissionRole extends JpaRepository<PermissionRole, Integer> {
    List<PermissionRole> findByPageRoleID_PageRoleID(int pageRoleID);
    List<PermissionRole> findByPermissionType(String permissionType);
    boolean existsByPermissionTypeAndPageRoleID_PageRoleID(String permissionType, int pageRoleID);
}