package com.sena.crud_basic.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import com.sena.crud_basic.model.Delivery;

import com.sena.crud_basic.DTO.DeliveryDTO;
import com.sena.crud_basic.DTO.responseDTO;
import com.sena.crud_basic.service.DeliveryService;

@RestController
@RequestMapping("/api/v1/deliveries")
public class DeliveryController {

    @Autowired
    private DeliveryService deliveryService;

    // Registrar una nueva entrega con validaciones
    @PostMapping("/")
    public ResponseEntity<Object> register(@RequestBody DeliveryDTO deliveryDTO) {
        responseDTO respuesta = deliveryService.save(deliveryDTO);
        if (respuesta.getStatus().equals(HttpStatus.OK.toString())) {
            return new ResponseEntity<>(respuesta, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(respuesta, HttpStatus.BAD_REQUEST);
        }
    }

    // Consultar todas las entregas
    @GetMapping("/")
    public ResponseEntity<Object> getAll() {
        return new ResponseEntity<>(deliveryService.findAll(), HttpStatus.OK);
    }

    // Consultar una entrega por su ID
    @GetMapping("/{id}")
    public ResponseEntity<Object> getById(@PathVariable int id) {
        var delivery = deliveryService.findById(id);
        if (!delivery.isPresent()) {
            return new ResponseEntity<>("", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(delivery.get(), HttpStatus.OK);
    }

    // Eliminar una entrega por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteById(@PathVariable int id) {
        responseDTO message = deliveryService.deleteUser(id);
        if (message.getStatus().equals(HttpStatus.OK.toString())) {
            return new ResponseEntity<>(message, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        }
    }

    
    // Filtrar entregas por fecha y estado
    @GetMapping("/filter")
    public ResponseEntity<List<Delivery>> filterDeliveries(
            @RequestParam(required = false) LocalDate deliveryDate,
            @RequestParam(required = false) Boolean status) {

        List<Delivery> result = deliveryService.filterDeliveries(deliveryDate, status);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    // En DeliveryController.java
@PutMapping("/{id}")
public ResponseEntity<responseDTO> update(
        @PathVariable int id,
        @RequestBody DeliveryDTO dto) {
    
    responseDTO response = deliveryService.update(id, dto);
    if (response.getStatus().equals(HttpStatus.OK.toString())) {
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
}

}
