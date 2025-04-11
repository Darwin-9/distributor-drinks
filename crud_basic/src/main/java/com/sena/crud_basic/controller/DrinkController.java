package com.sena.crud_basic.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.sena.crud_basic.DTO.DrinkDTO;
import com.sena.crud_basic.DTO.responseDTO;
import com.sena.crud_basic.service.DrinkService;

@RestController
@RequestMapping("/api/v1/drinks")
public class DrinkController { 

    @Autowired
    private DrinkService drinkService;

    // Registrar una nueva bebida con validaciones
    @PostMapping("/")
    public ResponseEntity<Object> register(@RequestBody DrinkDTO drinkDTO) {
        responseDTO respuesta = drinkService.save(drinkDTO);
        if (respuesta.getStatus().equals(HttpStatus.OK.toString())) {
            return new ResponseEntity<>(respuesta, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(respuesta, HttpStatus.BAD_REQUEST);
        }
    }

    // Consultar todas las bebidas
    @GetMapping("/")
    public ResponseEntity<Object> getAll() {
        return new ResponseEntity<>(drinkService.findAll(), HttpStatus.OK);
    }

    // Consultar una bebida por su ID
    @GetMapping("/{id}")
    public ResponseEntity<Object> getById(@PathVariable int id) {
        var drink = drinkService.findById(id);
        if (!drink.isPresent()) {
            return new ResponseEntity<>("", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(drink.get(), HttpStatus.OK);
    }

    // Eliminar una bebida por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteById(@PathVariable int id) {
        responseDTO message = drinkService.deleteUser(id);
        if (message.getStatus().equals(HttpStatus.OK.toString())) {
            return new ResponseEntity<>(message, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        }
    }
}
