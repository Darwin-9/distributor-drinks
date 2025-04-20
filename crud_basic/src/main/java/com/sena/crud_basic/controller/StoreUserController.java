package com.sena.crud_basic.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import com.sena.crud_basic.model.StoreUser;

import com.sena.crud_basic.DTO.StoreUserDTO;
import com.sena.crud_basic.DTO.responseDTO;
import com.sena.crud_basic.service.StoreUserService;

@RestController
@RequestMapping("/api/v1/store-users")
public class StoreUserController {

    @Autowired
    private StoreUserService storeUserService;

    // Registrar un nuevo usuario de tienda con validaciones
    @PostMapping("/")
    public ResponseEntity<Object> register(@RequestBody StoreUserDTO storeUserDTO) {
        responseDTO respuesta = storeUserService.save(storeUserDTO);
        if (respuesta.getStatus().equals(HttpStatus.OK.toString())) {
            return new ResponseEntity<>(respuesta, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(respuesta, HttpStatus.BAD_REQUEST);
        }
    }

    // Consultar todos los usuarios de tienda
    @GetMapping("/")
    public ResponseEntity<Object> getAll() {
        return new ResponseEntity<>(storeUserService.findAll(), HttpStatus.OK);
    }

    // Consultar un usuario de tienda por su ID
    @GetMapping("/{id}")
    public ResponseEntity<Object> getById(@PathVariable int id) {
        var storeUser = storeUserService.findById(id);
        if (!storeUser.isPresent()) {
            return new ResponseEntity<>("", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(storeUser.get(), HttpStatus.OK);
    }

   // Eliminar
   @DeleteMapping("/{id}")
   public ResponseEntity<Object> delete(@PathVariable int id) {
       responseDTO response = storeUserService.delete(id);
       if (response.getStatus().equals(HttpStatus.OK.toString())) {
           return new ResponseEntity<>(response, HttpStatus.OK);
       }
       return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
   }

   // Filtro
   @GetMapping("/filter")
   public ResponseEntity<List<StoreUser>> filterStoreUsers(
           @RequestParam(required = false) String username,
           @RequestParam(required = false) String email,
           @RequestParam(required = false) Boolean status) {

       List<StoreUser> result = storeUserService.filterStoreUsers(username, email, status);
       return new ResponseEntity<>(result, HttpStatus.OK);
       
   }

   
   // Actualizar
   @PutMapping("/{id}")
   public ResponseEntity<Object> update(@PathVariable int id, @RequestBody StoreUserDTO dto) {
       responseDTO response = storeUserService.updateStoreUser(id, dto);
       if (response.getStatus().equals(HttpStatus.OK.toString())) {
           return new ResponseEntity<>(response, HttpStatus.OK);
       }
       return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
   }

}
