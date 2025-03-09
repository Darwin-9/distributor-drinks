package com.sena.crud_basic.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.sena.crud_basic.DTO.ZoneDTO;
import com.sena.crud_basic.service.ZoneService;

@RestController
@RequestMapping("/api/v1/zone")
public class ZoneController {

    /*
     * GET
     * POST(REGISTER)
     * PUT
     * DELETE
     */
    @Autowired
    private ZoneService zoneService;

    @PostMapping("/")
    public ResponseEntity<Object> registerZone(@RequestBody ZoneDTO zone) {
        zoneService.save(zone);
        return new ResponseEntity<>("Register OK", HttpStatus.OK);
    }
}
