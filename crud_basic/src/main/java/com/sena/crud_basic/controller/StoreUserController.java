package com.sena.crud_basic.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.sena.crud_basic.DTO.StoreUserDTO;
import com.sena.crud_basic.DTO.responseDTO;
import com.sena.crud_basic.service.StoreUserService;

@RestController
@RequestMapping("/api/v1/store-users")
public class StoreUserController {

    @Autowired
    private StoreUserService storeUserService;

    // Registrar un nuevo usuario de tienda con validaciones
    @PostMapping("/")
    public ResponseEntity<Object> register(@RequestBody StoreUserDTO storeUserDTO) {
        responseDTO respuesta = storeUserService.save(storeUserDTO);
        if (respuesta.getStatus().equals(HttpStatus.OK.toString())) {
            return new ResponseEntity<>(respuesta, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(respuesta, HttpStatus.BAD_REQUEST);
        }
    }

    // Consultar todos los usuarios de tienda
    @GetMapping("/")
    public ResponseEntity<Object> getAll() {
        return new ResponseEntity<>(storeUserService.findAll(), HttpStatus.OK);
    }

    // Consultar un usuario de tienda por su ID
    @GetMapping("/{id}")
    public ResponseEntity<Object> getById(@PathVariable int id) {
        var storeUser = storeUserService.findById(id);
        if (!storeUser.isPresent()) {
            return new ResponseEntity<>("", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(storeUser.get(), HttpStatus.OK);
    }

    // Eliminar un usuario de tienda por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteById(@PathVariable int id) {
        responseDTO message = storeUserService.deleteUser(id);
        if (message.getStatus().equals(HttpStatus.OK.toString())) {
            return new ResponseEntity<>(message, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        }
    }
}
