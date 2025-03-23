package com.sena.crud_basic.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.sena.crud_basic.DTO.OrderDrinkDTO;
import com.sena.crud_basic.DTO.responseDTO;
import com.sena.crud_basic.service.OrderDrinkService;

@RestController
@RequestMapping("/api/v1/order-drinks")
public class OrderDrinkController {

    @Autowired
    private OrderDrinkService orderDrinkService;

    // Registrar un nuevo detalle de pedido con validaciones
    @PostMapping("/")
    public ResponseEntity<Object> register(@RequestBody OrderDrinkDTO orderDrinkDTO) {
        responseDTO respuesta = orderDrinkService.save(orderDrinkDTO);
        if (respuesta.getStatus().equals(HttpStatus.OK.toString())) {
            return new ResponseEntity<>(respuesta, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(respuesta, HttpStatus.BAD_REQUEST);
        }
    }

    // Consultar todos los detalles de pedidos
    @GetMapping("/")
    public ResponseEntity<Object> getAll() {
        return new ResponseEntity<>(orderDrinkService.findAll(), HttpStatus.OK);
    }

    // Consultar un detalle de pedido por su ID
    @GetMapping("/{id}")
    public ResponseEntity<Object> getById(@PathVariable int id) {
        var orderDrink = orderDrinkService.findById(id);
        if (!orderDrink.isPresent()) {
            return new ResponseEntity<>("", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(orderDrink.get(), HttpStatus.OK);
    }

    // Eliminar un detalle de pedido por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteById(@PathVariable int id) {
        responseDTO message = orderDrinkService.deleteUser(id);
        if (message.getStatus().equals(HttpStatus.OK.toString())) {
            return new ResponseEntity<>(message, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        }
    }
}
