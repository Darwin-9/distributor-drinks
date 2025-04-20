package com.sena.crud_basic.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

import com.sena.crud_basic.DTO.InventoryDTO;
import com.sena.crud_basic.DTO.responseDTO;
import com.sena.crud_basic.model.Inventory;
import com.sena.crud_basic.service.InventoryService;

@RestController
@RequestMapping("/api/v1/inventory")
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;

    // Registrar un nuevo registro de inventario con validaciones
    @PostMapping("/")
    public ResponseEntity<Object> register(@RequestBody InventoryDTO inventoryDTO) {
        responseDTO respuesta = inventoryService.save(inventoryDTO);
        if (respuesta.getStatus().equals(HttpStatus.OK.toString())) {
            return new ResponseEntity<>(respuesta, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(respuesta, HttpStatus.BAD_REQUEST);
        }
    }

    // Consultar todos los registros de inventario
    @GetMapping("/")
    public ResponseEntity<Object> getAll() {
        return new ResponseEntity<>(inventoryService.findAll(), HttpStatus.OK);
    }

    // Consultar un registro de inventario por su ID
    @GetMapping("/{id}")
    public ResponseEntity<Object> getById(@PathVariable int id) {
        var inventory = inventoryService.findById(id);
        if (!inventory.isPresent()) {
            return new ResponseEntity<>("", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(inventory.get(), HttpStatus.OK);
    }

    // Eliminar un registro de inventario por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteById(@PathVariable int id) {
        responseDTO message = inventoryService.deleteUser(id);
        if (message.getStatus().equals(HttpStatus.OK.toString())) {
            return new ResponseEntity<>(message, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable int id, @RequestBody InventoryDTO dto) {
        responseDTO response = inventoryService.update(id, dto);
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getStatus()));
    }

    @GetMapping("/filter")
    public ResponseEntity<List<Inventory>> filterInventory(
            @RequestParam(required = false) String last_update,
            @RequestParam(required = false) Integer current_stock) {

        List<Inventory> result = inventoryService.filterInventory(last_update, current_stock);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}
