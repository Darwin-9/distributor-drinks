package com.sena.crud_basic.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.sena.crud_basic.DTO.AdminDTO;
import com.sena.crud_basic.DTO.responseDTO;
import com.sena.crud_basic.model.Admin;
import com.sena.crud_basic.repository.IAdmin;

@Service
public class AdminService {

    @Autowired
    private IAdmin data;

    // Método para guardar un administrador con validaciones
    public responseDTO save(AdminDTO adminDTO) {
        if (adminDTO.getUsername().length() < 3 || adminDTO.getUsername().length() > 50) {
            return new responseDTO(HttpStatus.BAD_REQUEST.toString(), "El nombre de usuario debe tener entre 3 y 50 caracteres");
        }

        if (!isValidEmail(adminDTO.getEmail())) {
            return new responseDTO(HttpStatus.BAD_REQUEST.toString(), "El correo electrónico no tiene un formato válido");
        }

        Admin admin = convertToModel(adminDTO);
        data.save(admin);

        return new responseDTO(HttpStatus.OK.toString(), "Administrador guardado exitosamente");
    }

    // Método para obtener todos los administradores
    public List<Admin> findAll() {
        return data.findAll();
    }

    // Método para buscar un administrador por ID
    public Optional<Admin> findById(int id) {
        return data.findById(id);
    }

    // Método para eliminar un administrador por ID
    public responseDTO deleteUser(int id) {
        Optional<Admin> admin = findById(id);
        if (!admin.isPresent()) {
            return new responseDTO(HttpStatus.BAD_REQUEST.toString(), "El administrador no existe");
        }
        data.deleteById(id);
        return new responseDTO(HttpStatus.OK.toString(), "Administrador eliminado correctamente");
    }

    // Método para convertir un modelo a un DTO
    public AdminDTO convertToDTO(Admin admin) {
        return new AdminDTO(admin.getUsername(), admin.getPassword(), admin.getEmail());
    }

    // Método para convertir un DTO a un modelo
    public Admin convertToModel(AdminDTO adminDTO) {
        return new Admin(0, adminDTO.getUsername(), adminDTO.getPassword(), adminDTO.getEmail());
    }

    // Método para validar el formato del correo electrónico
    private boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        return email.matches(emailRegex);
    }
}
