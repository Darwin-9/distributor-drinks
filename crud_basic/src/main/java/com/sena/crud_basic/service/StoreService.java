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
    //metodo para eliminar una tienda por id logicamente con status
    public responseDTO deleteUser(int id) {
        Optional<Store> store = findById(id);
        if (!store.isPresent()) {
            return new responseDTO(HttpStatus.BAD_REQUEST.toString(), "La tienda no existe");
        }
    
        store.get().setStatus(false);
        data.save(store.get());
    
        return new responseDTO(HttpStatus.OK.toString(), "Tienda eliminada correctamente");
    }
    

    public responseDTO update(StoreDTO storeDTO, int id) {
        Optional<Store> storeOptional = findById(id);
        if (!storeOptional.isPresent()) {
            return new responseDTO(HttpStatus.BAD_REQUEST.toString(), "La tienda no existe");
        }
    
        if (storeDTO.getName() == null || storeDTO.getName().trim().isEmpty()) {
            return new responseDTO(HttpStatus.BAD_REQUEST.toString(), "El nombre no puede estar vacío");
        }
        if (storeDTO.getName().length() < 1 || storeDTO.getName().length() > 100) {
            return new responseDTO(HttpStatus.BAD_REQUEST.toString(), "El nombre debe tener entre 1 y 100 caracteres");
        }
    
        Store store = storeOptional.get();
        store.setName(storeDTO.getName());
        store.setAddress(storeDTO.getAddress());
        store.setPhone_number(storeDTO.getPhone_number());
        store.setCity(storeDTO.getCity());
        data.save(store);
    
        return new responseDTO(HttpStatus.OK.toString(), "Tienda actualizada exitosamente");
    }

    public List<Store> filterStores(Integer id, String name, String address, String phone_number, String city) {
        return data.filterStores(id, name, address, phone_number, city);
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
