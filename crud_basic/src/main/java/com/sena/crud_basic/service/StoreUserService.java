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

    // Método para guardar un usuario de tienda con validaciones
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

    // Método para obtener todos los usuarios de tienda
    public List<StoreUser> findAll() {
        return data.findAll();
    }

    // Método para buscar un usuario de tienda por ID
    public Optional<StoreUser> findById(int id) {
        return data.findById(id);
    }

    // Método para eliminar un usuario de tienda por ID
    public responseDTO deleteUser(int id) {
        Optional<StoreUser> storeUser = findById(id);
        if (!storeUser.isPresent()) {
            return new responseDTO(HttpStatus.BAD_REQUEST.toString(), "El usuario de tienda no existe");
        }
        data.deleteById(id);
        return new responseDTO(HttpStatus.OK.toString(), "Usuario de tienda eliminado correctamente");
    }

    // Método para convertir un modelo a un DTO
    public StoreUserDTO convertToDTO(StoreUser storeUser) {
        return new StoreUserDTO(storeUser.getUsername(), storeUser.getEmail());
    }

    // Método para convertir un DTO a un modelo
    public StoreUser convertToModel(StoreUserDTO storeUserDTO) {
        return new StoreUser(0, storeUserDTO.getUsername(), null, storeUserDTO.getEmail(), null);
    }

    // Método para validar el formato del correo electrónico
    private boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        return email.matches(emailRegex);
    }
}
