package com.sena.crud_basic.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import com.sena.crud_basic.model.Store;

import com.sena.crud_basic.DTO.StoreDTO;
import com.sena.crud_basic.DTO.responseDTO;
import com.sena.crud_basic.service.StoreService;

@RestController
@RequestMapping("/api/v1/stores")
public class StoreController {

    @Autowired
    private StoreService storeService;

    // Registrar una nueva tienda con validaciones
    @PostMapping("/")
    public ResponseEntity<Object> register(@RequestBody StoreDTO storeDTO) {
        responseDTO respuesta = storeService.save(storeDTO);
        if (respuesta.getStatus().equals(HttpStatus.OK.toString())) {
            return new ResponseEntity<>(respuesta, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(respuesta, HttpStatus.BAD_REQUEST);
        }
    }

    // Consultar todas las tiendas
    @GetMapping("/")
    public ResponseEntity<Object> getAll() {
        return new ResponseEntity<>(storeService.findAll(), HttpStatus.OK);
    }

    // Consultar una tienda por su ID
    @GetMapping("/{id}")
    public ResponseEntity<Object> getById(@PathVariable int id) {
        var store = storeService.findById(id);
        if (!store.isPresent()) {
            return new ResponseEntity<>("", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(store.get(), HttpStatus.OK);
    }

   // Eliminar tienda (lógica)
   @DeleteMapping("/{id}")
   public ResponseEntity<Object> deleteStore(@PathVariable int id) {
       responseDTO response = storeService.deleteUser(id);
       if (response.getStatus().equals(HttpStatus.OK.toString())) {
           return new ResponseEntity<>(response, HttpStatus.OK);
       } else {
           return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
       }
   }

    // Filtrar
    @GetMapping("/filter")
    public ResponseEntity<List<Store>> filterStores(
            @RequestParam(required = false) Integer id,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String address,
            @RequestParam(required = false) String phone_number,
            @RequestParam(required = false) String city) {

        List<Store> stores = storeService.filterStores(id, name, address, phone_number, city);
        return new ResponseEntity<>(stores, HttpStatus.OK);
    }

    // Actualizar tienda
    @PutMapping("/{id}")
    public ResponseEntity<Object> updateStore(@PathVariable int id, @RequestBody StoreDTO storeDTO) {
        responseDTO response = storeService.update(storeDTO, id);
        if (response.getStatus().equals(HttpStatus.OK.toString())) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }
}
