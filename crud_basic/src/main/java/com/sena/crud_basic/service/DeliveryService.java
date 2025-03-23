package com.sena.crud_basic.service;

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

    // Método para eliminar una entrega por ID
    public responseDTO deleteUser(int id) {
        Optional<Delivery> delivery = findById(id);
        if (!delivery.isPresent()) {
            return new responseDTO(HttpStatus.BAD_REQUEST.toString(), "La entrega no existe");
        }
        data.deleteById(id);
        return new responseDTO(HttpStatus.OK.toString(), "Entrega eliminada correctamente");
    }

    // Método para convertir un modelo a un DTO
    public DeliveryDTO convertToDTO(Delivery delivery) {
        return new DeliveryDTO(delivery.getDelivery_date());
    }

    // Método para convertir un DTO a un modelo
    public Delivery convertToModel(DeliveryDTO deliveryDTO) {
        return new Delivery(0, null, null, null, deliveryDTO.getDelivery_date());
    }
}
