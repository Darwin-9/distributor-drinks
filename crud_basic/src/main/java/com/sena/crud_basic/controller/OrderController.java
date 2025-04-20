package com.sena.crud_basic.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import com.sena.crud_basic.model.Order;

import com.sena.crud_basic.DTO.OrderDTO;
import com.sena.crud_basic.DTO.responseDTO;
import com.sena.crud_basic.service.OrderService;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    // Registrar un nuevo pedido con validaciones
    @PostMapping("/")
    public ResponseEntity<Object> register(@RequestBody OrderDTO orderDTO) {
        responseDTO respuesta = orderService.save(orderDTO);
        if (respuesta.getStatus().equals(HttpStatus.OK.toString())) {
            return new ResponseEntity<>(respuesta, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(respuesta, HttpStatus.BAD_REQUEST);
        }
    }

    // Consultar todos los pedidos
    @GetMapping("/")
    public ResponseEntity<Object> getAll() {
        return new ResponseEntity<>(orderService.findAll(), HttpStatus.OK);
    }

    // Consultar un pedido por su ID
    @GetMapping("/{id}")
    public ResponseEntity<Object> getById(@PathVariable int id) {
        var order = orderService.findById(id);
        if (!order.isPresent()) {
            return new ResponseEntity<>("", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(order.get(), HttpStatus.OK);
    }

    // Eliminar
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable int id) {
        responseDTO response = orderService.delete(id);
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getStatus()));
    }

    // Actualizar
    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable int id, @RequestBody OrderDTO dto) {
        responseDTO response = orderService.updateOrder(id, dto);
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getStatus()));
    }


    // Filtrar por fecha y status
    @GetMapping("/filter")
    public ResponseEntity<List<Order>> filterOrders(
            @RequestParam(required = false) String order_date,
            @RequestParam(required = false) Boolean status) {

        List<Order> result = orderService.filterOrders(order_date, status);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }


}
