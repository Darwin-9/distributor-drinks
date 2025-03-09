package com.sena.crud_basic.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.sena.crud_basic.DTO.TruckDTO;
import com.sena.crud_basic.service.TruckService;

@RestController
@RequestMapping("/api/v1/truck")
public class TruckController {

    /*
     * GET
     * POST(REGISTER)
     * PUT
     * DELETE
     */
    @Autowired
    private TruckService truckService;

    @PostMapping("/")
    public ResponseEntity<Object> registerTruck(@RequestBody TruckDTO truck) {
        truckService.save(truck);
        return new ResponseEntity<>("Register OK", HttpStatus.OK);
    }
}
