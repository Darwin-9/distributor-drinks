package com.sena.crud_basic.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.sena.crud_basic.DTO.StoreDTO;
import com.sena.crud_basic.DTO.responseDTO;
import com.sena.crud_basic.service.StoreService;

@RestController
@RequestMapping("/api/v1/stores")
public class StoreController {

    @Autowired
    private StoreService storeService;

    // Registrar una nueva tienda con validaciones
    @PostMapping("/")
    public ResponseEntity<Object> register(@RequestBody StoreDTO storeDTO) {
        responseDTO respuesta = storeService.save(storeDTO);
        if (respuesta.getStatus().equals(HttpStatus.OK.toString())) {
            return new ResponseEntity<>(respuesta, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(respuesta, HttpStatus.BAD_REQUEST);
        }
    }

    // Consultar todas las tiendas
    @GetMapping("/")
    public ResponseEntity<Object> getAll() {
        return new ResponseEntity<>(storeService.findAll(), HttpStatus.OK);
    }

    // Consultar una tienda por su ID
    @GetMapping("/{id}")
    public ResponseEntity<Object> getById(@PathVariable int id) {
        var store = storeService.findById(id);
        if (!store.isPresent()) {
            return new ResponseEntity<>("", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(store.get(), HttpStatus.OK);
    }

    // Eliminar una tienda por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteById(@PathVariable int id) {
        responseDTO message = storeService.deleteUser(id);
        if (message.getStatus().equals(HttpStatus.OK.toString())) {
            return new ResponseEntity<>(message, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        }
    }
}
