package com.sena.crud_basic.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.sena.crud_basic.DTO.TruckDTO;
import com.sena.crud_basic.DTO.responseDTO;
import com.sena.crud_basic.model.Truck;
import com.sena.crud_basic.repository.ITruck;

@Service
public class TruckService {

    @Autowired
    private ITruck data; 

    // Método para guardar un camión con validaciones
    public responseDTO save(TruckDTO truckDTO) {
        if (truckDTO.getModel().length() < 1 || truckDTO.getModel().length() > 50) {
            return new responseDTO(HttpStatus.BAD_REQUEST.toString(), "El modelo debe tener entre 1 y 50 caracteres");
        }

        if (truckDTO.getCapacity() <= 0) {
            return new responseDTO(HttpStatus.BAD_REQUEST.toString(), "La capacidad debe ser mayor a 0");
        }

        Truck truck = convertToModel(truckDTO);
        data.save(truck);

        return new responseDTO(HttpStatus.OK.toString(), "Camión guardado exitosamente");
    }

    // Método para obtener todos los camiones
    public List<Truck> findAll() {
        return data.findAll();
    }

    // Método para buscar un camión por ID
    public Optional<Truck> findById(int id) {
        return data.findById(id);
    }

    // Método para eliminar un camión por ID
    public responseDTO deleteUser(int id) {
        Optional<Truck> truck = findById(id);
        if (!truck.isPresent()) {
            return new responseDTO(HttpStatus.BAD_REQUEST.toString(), "El camión no existe");
        }
        // Cambiar el estado del conductor a false (eliminación lógica)
        truck.get().setStatus(false);
        data.save(truck.get());

        // Devolvemos una respuesta de éxito
        return new responseDTO(
            HttpStatus.OK.toString(),
            "Camion eliminado correctamente");
    }


    public List<Truck> filterTrucks(Integer id, String model, Double capacity, String plateNumber) {
        return data.filterTrucks(id, model, capacity, plateNumber);
    }
    

    public responseDTO update(TruckDTO truckDTO, int id) {
        Optional<Truck> truckOptional = findById(id);
        if (!truckOptional.isPresent()) {
            return new responseDTO(HttpStatus.BAD_REQUEST.toString(), "El camión no existe");
        }
    
        if (truckDTO.getModel() == null || truckDTO.getModel().trim().isEmpty()) {
            return new responseDTO(HttpStatus.BAD_REQUEST.toString(), "El modelo no puede estar vacío");
        }
        if (truckDTO.getModel().length() < 1 || truckDTO.getModel().length() > 50) {
            return new responseDTO(HttpStatus.BAD_REQUEST.toString(), "El modelo debe tener entre 1 y 50 caracteres");
        }
    
        if (truckDTO.getCapacity() <= 0) {
            return new responseDTO(HttpStatus.BAD_REQUEST.toString(), "La capacidad debe ser mayor a 0");
        }
    
        Truck truck = truckOptional.get();
        truck.setModel(truckDTO.getModel());
        truck.setCapacity(truckDTO.getCapacity());
        truck.setPlate_number(truckDTO.getPlate_number());
        data.save(truck);
    
        return new responseDTO(HttpStatus.OK.toString(), "Camión actualizado exitosamente");
    }
    

    // Método para convertir un modelo a un DTO
    public TruckDTO convertToDTO(Truck truck) {
        return new TruckDTO(truck.getCapacity(), truck.getModel(), truck.getPlate_number());
    }

    // Método para convertir un DTO a un modelo
    public Truck convertToModel(TruckDTO truckDTO) {
        return new Truck(0, truckDTO.getCapacity(), truckDTO.getModel(), truckDTO.getPlate_number(), true);
    }
}
