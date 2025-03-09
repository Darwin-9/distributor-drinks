package com.sena.crud_basic.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.sena.crud_basic.DTO.DistributorDTO;
import com.sena.crud_basic.service.DistributorService;

@RestController
@RequestMapping("/api/v1/distributor")
public class DistributorController {

    /*
     * GET
     * POST(REGISTER)
     * PUT
     * DELETE
     */
    @Autowired
    private DistributorService distributorService;

    @PostMapping("/")
    public ResponseEntity<Object> registerDistributor(@RequestBody DistributorDTO distributor) {
        distributorService.save(distributor);
        return new ResponseEntity<>("Register OK", HttpStatus.OK);
    }
}
