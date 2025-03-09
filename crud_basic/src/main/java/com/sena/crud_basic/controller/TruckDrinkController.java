package com.sena.crud_basic.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.sena.crud_basic.DTO.TruckDrinkDTO;
import com.sena.crud_basic.service.TruckDrinkService;

@RestController
@RequestMapping("/api/v1/truckDrink")
public class TruckDrinkController {

    /*
     * GET
     * POST(REGISTER)
     * PUT
     * DELETE
     */
    @Autowired
    private TruckDrinkService truckDrinkService;

    @PostMapping("/")
    public ResponseEntity<Object> registerTruckDrink(@RequestBody TruckDrinkDTO truckDrink) {
        truckDrinkService.save(truckDrink);
        return new ResponseEntity<>("Register OK", HttpStatus.OK);
    }
}
