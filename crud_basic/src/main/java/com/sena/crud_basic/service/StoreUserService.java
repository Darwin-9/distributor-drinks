package com.sena.crud_basic.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.sena.crud_basic.DTO.StoreUserDTO;
import com.sena.crud_basic.DTO.responseDTO;
import com.sena.crud_basic.model.StoreUser;
import com.sena.crud_basic.repository.IStore;
import com.sena.crud_basic.repository.IStoreUser;
import com.sena.crud_basic.model.Store;

@Service
public class StoreUserService {

    @Autowired
    private IStoreUser data;

    @Autowired
    private IStore storeRepository;

    // Guardar con validaciones
    public responseDTO save(StoreUserDTO storeUserDTO) {
        // Validaciones básicas
        if (storeUserDTO.getUsername().length() < 3 || storeUserDTO.getUsername().length() > 50) {
            return new responseDTO(HttpStatus.BAD_REQUEST.toString(), "El nombre de usuario debe tener entre 3 y 50 caracteres");
        }

        if (storeUserDTO.getPassword().length() < 4) {
            return new responseDTO(HttpStatus.BAD_REQUEST.toString(), "La contraseña debe tener al menos 4 caracteres");
        }

        // Validar que la tienda exista
        Store store = storeRepository.findById(storeUserDTO.getStore_id())
            .orElseThrow(() -> new RuntimeException("Tienda no encontrada"));

        StoreUser storeUser = convertToModel(storeUserDTO);
        storeUser.setStore(store);
        data.save(storeUser);

        return new responseDTO(HttpStatus.OK.toString(), "Usuario de tienda guardado exitosamente");
    }

    // Obtener todos
    public List<StoreUser> findAll() {
        return data.findAll();
    }

    // Buscar por ID
    public Optional<StoreUser> findById(int id) {
        return data.findById(id);
    }

    // Eliminación lógica
    public responseDTO delete(int id) {
        Optional<StoreUser> storeUser = findById(id);
        if (!storeUser.isPresent()) {
            return new responseDTO(HttpStatus.NOT_FOUND.toString(), "El usuario de tienda no existe");
        }

        StoreUser existingUser = storeUser.get();
        existingUser.setStatus(false);
        data.save(existingUser);

        return new responseDTO(HttpStatus.OK.toString(), "Usuario de tienda eliminado correctamente");
    }

    // Convertir a DTO
    public StoreUserDTO convertToDTO(StoreUser storeUser) {
        return new StoreUserDTO(storeUser.getUsername(), storeUser.getEmail(), storeUser.getPassword(), storeUser.getStore().getStore_id());
    }

    // Convertir a modelo
    public StoreUser convertToModel(StoreUserDTO storeUserDTO) {
        Store store = storeRepository.findById(storeUserDTO.getStore_id())
            .orElseThrow(() -> new RuntimeException("Tienda no encontrada"));
            
        return new StoreUser(
            0, 
            storeUserDTO.getUsername(), 
            storeUserDTO.getPassword(), 
            storeUserDTO.getEmail(), 
            store, 
            true
        );
    }



 // Actualizar usuario con tienda
public responseDTO updateStoreUser(int id, StoreUserDTO dto) {
    Optional<StoreUser> storeUserOpt = data.findById(id);
    if (!storeUserOpt.isPresent()) {
        return new responseDTO(HttpStatus.NOT_FOUND.toString(), "El usuario de tienda no existe");
    }

    // Validaciones básicas
    if (dto.getUsername() == null || dto.getUsername().length() < 3 || dto.getUsername().length() > 50) {
        return new responseDTO(HttpStatus.BAD_REQUEST.toString(), "El nombre de usuario debe tener entre 3 y 50 caracteres");
    }

    // Obtener la tienda
    Store store = storeRepository.findById(dto.getStore_id())
        .orElseThrow(() -> new RuntimeException("Tienda no encontrada"));

    StoreUser existingUser = storeUserOpt.get();
    existingUser.setUsername(dto.getUsername());
    existingUser.setEmail(dto.getEmail());
    existingUser.setStore(store); // Actualizar la tienda

    data.save(existingUser);

    return new responseDTO(HttpStatus.OK.toString(), "Usuario de tienda actualizado correctamente");
}

     // Filtro
     public List<StoreUser> filterStoreUsers(String searchTerm) {
        if (searchTerm == null || searchTerm.isEmpty()) {
            return data.findAll();
        }
        return data.filterStoreUsers(searchTerm.toLowerCase());
    }
    
}
