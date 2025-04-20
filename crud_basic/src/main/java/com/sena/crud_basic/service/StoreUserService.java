package com.sena.crud_basic.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.sena.crud_basic.DTO.StoreUserDTO;
import com.sena.crud_basic.DTO.responseDTO;
import com.sena.crud_basic.model.StoreUser;
import com.sena.crud_basic.repository.IStoreUser;

@Service
public class StoreUserService {

    @Autowired
    private IStoreUser data;

    // Guardar con validaciones
    public responseDTO save(StoreUserDTO storeUserDTO) {
        if (storeUserDTO.getUsername().length() < 3 || storeUserDTO.getUsername().length() > 50) {
            return new responseDTO(HttpStatus.BAD_REQUEST.toString(), "El nombre de usuario debe tener entre 3 y 50 caracteres");
        }

        if (!isValidEmail(storeUserDTO.getEmail())) {
            return new responseDTO(HttpStatus.BAD_REQUEST.toString(), "El correo electrónico no tiene un formato válido");
        }

        StoreUser storeUser = convertToModel(storeUserDTO);
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
        return new StoreUser(0, storeUserDTO.getUsername(), storeUserDTO.getPassword() , storeUserDTO.getEmail(), null, true);
    }

    // Validar email
    private boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        return email.matches(emailRegex);
    }

  // Actualizar usuario con validaciones
  public responseDTO updateStoreUser(int id, StoreUserDTO dto) {
    Optional<StoreUser> storeUserOpt = data.findById(id);
    if (!storeUserOpt.isPresent()) {
        return new responseDTO(HttpStatus.NOT_FOUND.toString(), "El usuario de tienda con ID " + id + " no existe");
    }

    if (dto.getUsername() == null || dto.getUsername().length() < 3 || dto.getUsername().length() > 50) {
        return new responseDTO(HttpStatus.BAD_REQUEST.toString(), "El nombre de usuario debe tener entre 3 y 50 caracteres");
    }

    if (!isValidEmail(dto.getEmail())) {
        return new responseDTO(HttpStatus.BAD_REQUEST.toString(), "El correo electrónico no tiene un formato válido");
    }

    StoreUser existingUser = storeUserOpt.get();
    existingUser.setUsername(dto.getUsername());
    existingUser.setEmail(dto.getEmail());

    data.save(existingUser);

    return new responseDTO(HttpStatus.OK.toString(), "Usuario de tienda actualizado correctamente");
}

     // Filtro
     public List<StoreUser> filterStoreUsers(String username, String email, Boolean status) {
        return data.filterStoreUsers(username, email, status);
    }
    
}
