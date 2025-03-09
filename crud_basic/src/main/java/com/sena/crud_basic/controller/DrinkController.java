package com.sena.crud_basic.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.sena.crud_basic.DTO.DrinkDTO;
import com.sena.crud_basic.service.DrinkService;

@RestController
@RequestMapping("/api/v1/drink")
public class DrinkController {

    /*
     * GET
     * POST(REGISTER)
     * PUT
     * DELETE
     */
    @Autowired
    private DrinkService drinkService;

    @PostMapping("/")
    public ResponseEntity<Object> registerDrink(@RequestBody DrinkDTO drink) {
        drinkService.save(drink);
        return new ResponseEntity<>("Register OK", HttpStatus.OK);
    }
}
