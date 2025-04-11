package com.sena.crud_basic.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.sena.crud_basic.DTO.StoreDTO;
import com.sena.crud_basic.DTO.responseDTO;
import com.sena.crud_basic.model.Store;
import com.sena.crud_basic.repository.IStore;

@Service 
public class StoreService {

    @Autowired
    private IStore data;

    // Método para guardar una tienda con validaciones
    public responseDTO save(StoreDTO storeDTO) {
        if (storeDTO.getName().length() < 1 || storeDTO.getName().length() > 100) {
            return new responseDTO(HttpStatus.BAD_REQUEST.toString(), "El nombre debe tener entre 1 y 100 caracteres");
        }

        Store store = convertToModel(storeDTO);
        data.save(store);

        return new responseDTO(HttpStatus.OK.toString(), "Tienda guardada exitosamente");
    }

    // Método para obtener todas las tiendas
    public List<Store> findAll() {
        return data.findAll();
    }

    // Método para buscar una tienda por ID
    public Optional<Store> findById(int id) {
        return data.findById(id);
    }

    // Método para eliminar una tienda por ID
    public responseDTO deleteUser(int id) {
        Optional<Store> store = findById(id);
        if (!store.isPresent()) {
            return new responseDTO(HttpStatus.BAD_REQUEST.toString(), "La tienda no existe");
        }
        data.deleteById(id);
        return new responseDTO(HttpStatus.OK.toString(), "Tienda eliminada correctamente");
    }

    // Método para convertir un modelo a un DTO
    public StoreDTO convertToDTO(Store store) {
        return new StoreDTO(store.getName(), store.getAddress(), store.getPhone_number(), store.getCity());
    }

    // Método para convertir un DTO a un modelo
    public Store convertToModel(StoreDTO storeDTO) {
        return new Store(0, storeDTO.getName(), storeDTO.getAddress(), storeDTO.getPhone_number(), storeDTO.getCity(), true);
    }
}
