package com.sena.crud_basic.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sena.crud_basic.DTO.DistributorDTO;
import com.sena.crud_basic.DTO.responseDTO;
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
        responseDTO respuesta = distributorService.save(distributor);
        return new ResponseEntity<>(respuesta, HttpStatus.OK);
    }
    
    @GetMapping("/")
    public ResponseEntity<Object> getAllDistributor() {
        var listaUsuario = distributorService.findAll();
        // List<user> listaUsuariO2= distributorService.findAll();
        return new ResponseEntity<>(listaUsuario, HttpStatus.OK);
    }

    /*
     * Se requiere un dato, el ID
     * PathVariable=captura de información por la URL
     */
    @GetMapping("/{id}")
    public ResponseEntity<Object> getOneDistributor(@PathVariable int id) {
        var distributor = distributorService.findById(id);
        if (!distributor.isPresent())
            return new ResponseEntity<>("", HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(distributor, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteDistributor(@PathVariable int id) {
        var message= distributorService.deleteDistributor(id);
        
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}