package com.sena.crud_basic.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.sena.crud_basic.DTO.StoreDTO;
import com.sena.crud_basic.service.StoreService;

@RestController
@RequestMapping("/api/v1/store")
public class StoreController {

    /*
     * GET
     * POST(REGISTER)
     * PUT
     * DELETE
     */
    @Autowired
    private StoreService storeService;

    @PostMapping("/")
    public ResponseEntity<Object> registerStore(@RequestBody StoreDTO store) {
        storeService.save(store);
        return new ResponseEntity<>("Register OK", HttpStatus.OK);
    }
}
