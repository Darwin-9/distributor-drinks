package com.sena.crud_basic.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.sena.crud_basic.DTO.TruckDTO;
import com.sena.crud_basic.DTO.responseDTO;
import com.sena.crud_basic.model.Truck;
import com.sena.crud_basic.service.TruckService;

@RestController
@RequestMapping("/api/v1/trucks")
public class TruckController {

    @Autowired
    private TruckService truckService;

    // Registrar un nuevo camión con validaciones
    @PostMapping("/")
    public ResponseEntity<Object> register(@RequestBody TruckDTO truckDTO) {
        responseDTO respuesta = truckService.save(truckDTO);
        if (respuesta.getStatus().equals(HttpStatus.OK.toString())) {
            return new ResponseEntity<>(respuesta, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(respuesta, HttpStatus.BAD_REQUEST);
        }
    }

    // Consultar todos los camiones
    @GetMapping("/")
    public ResponseEntity<Object> getAll() {
        return new ResponseEntity<>(truckService.findAll(), HttpStatus.OK);
    }

    // Consultar un camión por su ID
    @GetMapping("/{id}")
    public ResponseEntity<Object> getById(@PathVariable int id) {
        var truck = truckService.findById(id);
        if (!truck.isPresent()) {
            return new ResponseEntity<>("", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(truck.get(), HttpStatus.OK);
    }

    // Eliminar un camión por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteById(@PathVariable int id) {
        responseDTO message = truckService.deleteUser(id);
        if (message.getStatus().equals(HttpStatus.OK.toString())) {
            return new ResponseEntity<>(message, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        }
    }

    // Filtrar
    @GetMapping("/filter")
    public ResponseEntity<List<Truck>> filterTrucks(
            @RequestParam(required = false) Integer id,
            @RequestParam(required = false) String model,
            @RequestParam(required = false) Double capacity,
            @RequestParam(required = false) String plateNumber) {

        List<Truck> trucks = truckService.filterTrucks(id, model, capacity, plateNumber);
        return new ResponseEntity<>(trucks, HttpStatus.OK);
    }

    // Actualizar camión
    @PutMapping("/{id}")
    public ResponseEntity<Object> updateTruck(@PathVariable int id, @RequestBody TruckDTO truckDTO) {
        responseDTO response = truckService.update(truckDTO, id);
        if (response.getStatus().equals(HttpStatus.OK.toString())) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }
}
