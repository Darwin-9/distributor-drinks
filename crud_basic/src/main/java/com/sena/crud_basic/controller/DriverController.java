package com.sena.crud_basic.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.sena.crud_basic.DTO.DriverDTO;
import com.sena.crud_basic.service.DriverService;

@RestController
@RequestMapping("/api/v1/driver")
public class DriverController {

    /*
     * GET
     * POST(REGISTER)
     * PUT
     * DELETE
     */
    @Autowired
    private DriverService driverService;

    @PostMapping("/")
    public ResponseEntity<Object> registerDriver(@RequestBody DriverDTO driver) {
        driverService.save(driver);
        return new ResponseEntity<>("Register OK", HttpStatus.OK);
    }
}
