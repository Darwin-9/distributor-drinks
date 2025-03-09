package com.sena.crud_basic.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.sena.crud_basic.DTO.OrderDTO;
import com.sena.crud_basic.service.OrderService;

@RestController
@RequestMapping("/api/v1/order")
public class OrderController {

    /*
     * GET
     * POST(REGISTER)
     * PUT
     * DELETE
     */
    @Autowired
    private OrderService orderService;

    @PostMapping("/")
    public ResponseEntity<Object> registerOrder(@RequestBody OrderDTO order) {
        orderService.save(order);
        return new ResponseEntity<>("Register OK", HttpStatus.OK);
    }
}
