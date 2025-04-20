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
import com.sena.crud_basic.model.Truck;
import com.sena.crud_basic.repository.IDelivery;
import com.sena.crud_basic.repository.IDriver;
import com.sena.crud_basic.repository.IOrder;
import com.sena.crud_basic.repository.ITruck;
import com.sena.crud_basic.model.Driver;
import com.sena.crud_basic.model.Order;

@Service
public class DeliveryService {

    @Autowired
    private IDelivery data;

    @Autowired
private IOrder orderRepository;

@Autowired
private ITruck truckRepository;

@Autowired
private IDriver driverRepository;

    // Método para guardar una entrega con validaciones
    public responseDTO save(DeliveryDTO deliveryDTO) {
    try {
        if (deliveryDTO.getDelivery_date() == null) {
            return new responseDTO(HttpStatus.BAD_REQUEST.toString(), "La fecha de entrega no puede ser nula");
        }

        // Obtener y validar las entidades relacionadas
        Order order = orderRepository.findById(deliveryDTO.getOrder())
            .orElseThrow(() -> new RuntimeException("Orden no encontrada"));
        
        Truck truck = truckRepository.findById(deliveryDTO.getTruck())
            .orElseThrow(() -> new RuntimeException("Camión no encontrado"));
            
        Driver driver = driverRepository.findById(deliveryDTO.getDriver())
            .orElseThrow(() -> new RuntimeException("Conductor no encontrado"));

        // Crear la entrega con todas las relaciones
        Delivery delivery = new Delivery();
        delivery.setOrder(order);
        delivery.setTruck(truck);
        delivery.setDriver(driver);
        delivery.setDelivery_date(deliveryDTO.getDelivery_date());
        delivery.setStatus(true);

        data.save(delivery);

        return new responseDTO(HttpStatus.OK.toString(), "Entrega guardada exitosamente");
    } catch (Exception e) {
        return new responseDTO(HttpStatus.INTERNAL_SERVER_ERROR.toString(), 
            "Error al guardar entrega: " + e.getMessage());
    }
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
        return new DeliveryDTO(delivery.getDelivery_date(), delivery.getOrder().getOrder_id(), delivery.getTruck().getTruck_id(), delivery.getDriver().getDriver_id());
    }

    // Método para convertir un DTO a un modelo
    public Delivery convertToModel(DeliveryDTO deliveryDTO) {
        return new Delivery(0, null, null, null, deliveryDTO.getDelivery_date(), true);
    }
}
