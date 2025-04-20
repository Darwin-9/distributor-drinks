package com.sena.crud_basic.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.sena.crud_basic.DTO.DeliveryDTO;
import com.sena.crud_basic.DTO.responseDTO;
import com.sena.crud_basic.model.Delivery;
import com.sena.crud_basic.repository.IDelivery;

@Service
public class DeliveryService {

    @Autowired
    private IDelivery data;

    // Método para guardar una entrega con validaciones
    public responseDTO save(DeliveryDTO deliveryDTO) {
        if (deliveryDTO.getDelivery_date() == null) {
            return new responseDTO(HttpStatus.BAD_REQUEST.toString(), "La fecha de entrega no puede ser nula");
        }

        Delivery delivery = convertToModel(deliveryDTO);
        data.save(delivery);

        return new responseDTO(HttpStatus.OK.toString(), "Entrega guardada exitosamente");
    }

    // Método para obtener todas las entregas
    public List<Delivery> findAll() {
        return data.findAll();
    }

    // Método para buscar una entrega por ID
    public Optional<Delivery> findById(int id) {
        return data.findById(id);
    }

// Eliminar (lógica con status)
    public responseDTO deleteUser(int id) {
        Optional<Delivery> delivery = findById(id);
        if (!delivery.isPresent()) {
            return new responseDTO(HttpStatus.BAD_REQUEST.toString(), "La entrega no existe");
        }

        delivery.get().setStatus(false);
        data.save(delivery.get());

        return new responseDTO(HttpStatus.OK.toString(), "Entrega eliminada correctamente");
    }

    
// En DeliveryService.java
public responseDTO update(int id, DeliveryDTO dto) {
    // 1. Validar si la entrega existe
    Optional<Delivery> deliveryOpt = findById(id);
    if (!deliveryOpt.isPresent()) {
        return new responseDTO(HttpStatus.NOT_FOUND.toString(), "La entrega con ID " + id + " no existe");
    }

    // 2. Validar campos del DTO (ej: fecha no nula)
    if (dto.getDelivery_date() == null) {
        return new responseDTO(HttpStatus.BAD_REQUEST.toString(), "La fecha de entrega no puede ser nula");
    }

    // 3. Actualizar los campos permitidos
    Delivery existingDelivery = deliveryOpt.get();
    existingDelivery.setDelivery_date(dto.getDelivery_date());
    // Puedes actualizar otros campos aquí si el DTO los incluye (ej: status)

    // 4. Guardar cambios
    data.save(existingDelivery);

    return new responseDTO(HttpStatus.OK.toString(), "Entrega actualizada correctamente");
}

// Método para filtrar entregas (agregar en DeliveryService)
public List<Delivery> filterDeliveries(LocalDate deliveryDate, Boolean status) {
    return data.filterDeliveries(deliveryDate, status);
}


    // Método para convertir un modelo a un DTO
    public DeliveryDTO convertToDTO(Delivery delivery) {
        return new DeliveryDTO(delivery.getDelivery_date());
    }

    // Método para convertir un DTO a un modelo
    public Delivery convertToModel(DeliveryDTO deliveryDTO) {
        return new Delivery(0, null, null, null, deliveryDTO.getDelivery_date(), true);
    }
}
