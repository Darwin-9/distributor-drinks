package com.sena.crud_basic.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.sena.crud_basic.DTO.AdminDTO;
import com.sena.crud_basic.DTO.responseDTO;
import com.sena.crud_basic.service.AdminService;

@RestController
@RequestMapping("/api/v1/admins")
public class AdminController {

    @Autowired
    private AdminService adminService;

    // Registrar un nuevo administrador con validaciones
    @PostMapping("/")
    public ResponseEntity<Object> register(@RequestBody AdminDTO adminDTO) {
        responseDTO respuesta = adminService.save(adminDTO);
        if (respuesta.getStatus().equals(HttpStatus.OK.toString())) {
            return new ResponseEntity<>(respuesta, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(respuesta, HttpStatus.BAD_REQUEST);
        }
    }

    // Consultar todos los administradores
    @GetMapping("/")
    public ResponseEntity<Object> getAll() {
        return new ResponseEntity<>(adminService.findAll(), HttpStatus.OK);
    }

    // Consultar un administrador por su ID
    @GetMapping("/{id}")
    public ResponseEntity<Object> getById(@PathVariable int id) {
        var admin = adminService.findById(id);
        if (!admin.isPresent()) {
            return new ResponseEntity<>("", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(admin.get(), HttpStatus.OK);
    }

    // Eliminar un administrador por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteById(@PathVariable int id) {
        responseDTO message = adminService.deleteUser(id);
        if (message.getStatus().equals(HttpStatus.OK.toString())) {
            return new ResponseEntity<>(message, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        }
    }
}
