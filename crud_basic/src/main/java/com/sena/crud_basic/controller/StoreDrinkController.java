package com.sena.crud_basic.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.sena.crud_basic.DTO.StoreDrinkDTO;
import com.sena.crud_basic.service.StoreDrinkService;

@RestController
@RequestMapping("/api/v1/storeDrink")
public class StoreDrinkController {

    /*
     * GET
     * POST(REGISTER)
     * PUT
     * DELETE
     */
    @Autowired
    private StoreDrinkService storeDrinkService;

    @PostMapping("/")
    public ResponseEntity<Object> registerStoreDrink(@RequestBody StoreDrinkDTO storeDrink) {
        storeDrinkService.save(storeDrink);
        return new ResponseEntity<>("Register OK", HttpStatus.OK);
    }
}
