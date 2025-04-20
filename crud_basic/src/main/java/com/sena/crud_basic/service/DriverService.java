package com.sena.crud_basic.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.sena.crud_basic.DTO.DriverDTO;
import com.sena.crud_basic.DTO.responseDTO;
import com.sena.crud_basic.model.Driver;
import com.sena.crud_basic.model.Truck;
import com.sena.crud_basic.repository.IDriver;
import com.sena.crud_basic.repository.ITruck;

@Service 
public class DriverService {

    @Autowired
    private IDriver data;

    @Autowired
    private ITruck truckRepository;

    // Método para guardar un conductor con validaciones
    public responseDTO save(DriverDTO driverDTO) {
        if (driverDTO.getFirst_name().length() < 1 || driverDTO.getFirst_name().length() > 50) {
            return new responseDTO(HttpStatus.BAD_REQUEST.toString(), "El nombre debe tener entre 1 y 50 caracteres");
        }

        if (driverDTO.getLast_name().length() < 1 || driverDTO.getLast_name().length() > 50) {
            return new responseDTO(HttpStatus.BAD_REQUEST.toString(), "El apellido debe tener entre 1 y 50 caracteres");
        }

        if (driverDTO.getLicense_number().length() < 5 || driverDTO.getLicense_number().length() > 20) {
            return new responseDTO(HttpStatus.BAD_REQUEST.toString(), "El número de licencia debe tener entre 5 y 20 caracteres");
        }

        Driver driver = convertToModel(driverDTO);
        data.save(driver);

        return new responseDTO(HttpStatus.OK.toString(), "Conductor guardado exitosamente");
    }

    // Método para obtener todos los conductores
    public List<Driver> findAll() {
        return data.findAll();
    }

    // Método para buscar un conductor por ID
    public Optional<Driver> findById(int id) {
        return data.findById(id);
    }

    // Método para eliminar un conductor por ID
    public responseDTO deleteUser(int id) {
        Optional<Driver> driver = findById(id);
        if (!driver.isPresent()) {
            return new responseDTO(HttpStatus.BAD_REQUEST.toString(), "El conductor no existe");
        }
         // Cambiar el estado del conductor a false (eliminación lógica)
         driver.get().setStatus(false);
         data.save(driver.get());
 
         // Devolvemos una respuesta de éxito
         return new responseDTO(
             HttpStatus.OK.toString(),
             "Conductor eliminado correctamente");
        
    }

    public responseDTO update(int id, DriverDTO dto) {
        // 1. Validar si el conductor existe
        Optional<Driver> driverOpt = findById(id);
        if (!driverOpt.isPresent()) {
            return new responseDTO(HttpStatus.NOT_FOUND.toString(), "El conductor con ID " + id + " no existe");
        }
    
        // 2. Validar campos del DTO
        if (dto.getFirst_name() == null || dto.getFirst_name().length() < 1 || dto.getFirst_name().length() > 50) {
            return new responseDTO(HttpStatus.BAD_REQUEST.toString(), "El nombre debe tener entre 1 y 50 caracteres");
        }
    
        if (dto.getLast_name() == null || dto.getLast_name().length() < 1 || dto.getLast_name().length() > 50) {
            return new responseDTO(HttpStatus.BAD_REQUEST.toString(), "El apellido debe tener entre 1 y 50 caracteres");
        }
    
        if (dto.getLicense_number() == null || dto.getLicense_number().length() < 5 || dto.getLicense_number().length() > 20) {
            return new responseDTO(HttpStatus.BAD_REQUEST.toString(), "El número de licencia debe tener entre 5 y 20 caracteres");
        }
    
        // 3. Obtener el camión asociado (si se actualiza truck_id)
        Truck truck = truckRepository.findById(dto.getTruck_id())
                .orElseThrow(() -> new RuntimeException("Camión no encontrado"));
    
        // 4. Actualizar los campos del conductor
        Driver existingDriver = driverOpt.get();
        existingDriver.setFirst_name(dto.getFirst_name());
        existingDriver.setLast_name(dto.getLast_name());
        existingDriver.setLicense_number(dto.getLicense_number());
        existingDriver.setTruck(truck); // Actualizar el camión si es necesario
    
        // 5. Guardar cambios
        data.save(existingDriver);
    
        return new responseDTO(HttpStatus.OK.toString(), "Conductor actualizado correctamente");
    }


public List<Driver> filterDrivers(String firstName, String lastName, String licenseNumber, Boolean status) {
    return data.filterDrivers(firstName, lastName, licenseNumber, status);
}


    // Método para convertir un modelo a un DTO
    public DriverDTO convertToDTO(Driver driver) {
        return new DriverDTO(driver.getFirst_name(), driver.getLast_name(), driver.getLicense_number(), 0);
    }

    // Método para convertir un DTO a un modelo
    public Driver convertToModel(DriverDTO driverDTO) {
        Truck truck = truckRepository.findById(driverDTO.getTruck_id()) // Obtener el camión desde la BD
            .orElseThrow(() -> new RuntimeException("Truck not found"));

    return new Driver(0, driverDTO.getFirst_name(), driverDTO.getLast_name(), driverDTO.getLicense_number(), truck, true);

    }

    
}
