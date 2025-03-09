package com.sena.crud_basic.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.sena.crud_basic.DTO.TrademarkDTO;
import com.sena.crud_basic.service.TrademarkService;

@RestController
@RequestMapping("/api/v1/trademark")
public class TrademarkController {

    /*
     * GET
     * POST(REGISTER)
     * PUT
     * DELETE
     */
    @Autowired
    private TrademarkService trademarkService;

    @PostMapping("/")
    public ResponseEntity<Object> registerTrademark(@RequestBody TrademarkDTO trademark) {
        trademarkService.save(trademark);
        return new ResponseEntity<>("Register OK", HttpStatus.OK);
    }
}
