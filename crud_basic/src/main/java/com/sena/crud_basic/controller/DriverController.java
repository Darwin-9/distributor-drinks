package com.sena.crud_basic.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.sena.crud_basic.DTO.DriverDTO;
import com.sena.crud_basic.DTO.responseDTO;
import com.sena.crud_basic.service.DriverService;

@RestController
@RequestMapping("/api/v1/drivers")
public class DriverController {

    @Autowired
    private DriverService driverService;

    // Registrar un nuevo conductor con validaciones
    @PostMapping("/")
    public ResponseEntity<Object> register(@RequestBody DriverDTO driverDTO) {
        responseDTO respuesta = driverService.save(driverDTO);
        if (respuesta.getStatus().equals(HttpStatus.OK.toString())) {
            return new ResponseEntity<>(respuesta, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(respuesta, HttpStatus.BAD_REQUEST);
        }
    }

    // Consultar todos los conductores
    @GetMapping("/")
    public ResponseEntity<Object> getAll() {
        return new ResponseEntity<>(driverService.findAll(), HttpStatus.OK);
    }

    // Consultar un conductor por su ID
    @GetMapping("/{id}")
    public ResponseEntity<Object> getById(@PathVariable int id) {
        var driver = driverService.findById(id);
        if (!driver.isPresent()) {
            return new ResponseEntity<>("", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(driver.get(), HttpStatus.OK);
    }

    // Eliminar un conductor por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteById(@PathVariable int id) {
        responseDTO message = driverService.deleteUser(id);
        if (message.getStatus().equals(HttpStatus.OK.toString())) {
            return new ResponseEntity<>(message, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        }
    }
}
