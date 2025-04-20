package com.sena.crud_basic.service;


import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.sena.crud_basic.DTO.OrderDrinkDTO;
import com.sena.crud_basic.DTO.responseDTO;
import com.sena.crud_basic.model.OrderDrink;
import com.sena.crud_basic.repository.IOrderDrink;
import java.util.List;

@Service
public class OrderDrinkService {

    @Autowired
    private IOrderDrink data;

    // Método para guardar un detalle de pedido con validaciones
    public responseDTO save(OrderDrinkDTO orderDrinkDTO) {
        if (orderDrinkDTO.getQuantity() <= 0) {
            return new responseDTO(HttpStatus.BAD_REQUEST.toString(), "La cantidad debe ser mayor a 0");
        }

        OrderDrink orderDrink = convertToModel(orderDrinkDTO);
        data.save(orderDrink);

        return new responseDTO(HttpStatus.OK.toString(), "Detalle de pedido guardado exitosamente");
    }

    // Método para obtener todos los detalles de pedidos
    public List<OrderDrink> findAll() {
        return data.findAll();
    }

    // Método para buscar un detalle de pedido por ID
    public Optional<OrderDrink> findById(int id) {
        return data.findById(id);
    }

    // Método para eliminar un detalle de pedido por ID
    public responseDTO deleteUser(int id) {
        Optional<OrderDrink> orderDrink = findById(id);
        if (!orderDrink.isPresent()) {
            return new responseDTO(HttpStatus.BAD_REQUEST.toString(), "El detalle de pedido no existe");
        }
        data.deleteById(id);
        return new responseDTO(HttpStatus.OK.toString(), "Detalle de pedido eliminado correctamente");
    }


    public responseDTO update(int id, OrderDrinkDTO dto) {
        // 1. Validar si el pedido existe
        Optional<OrderDrink> orderDrinkOpt = findById(id);
        if (!orderDrinkOpt.isPresent()) {
            return new responseDTO(HttpStatus.NOT_FOUND.toString(), "El pedido de bebida con ID " + id + " no existe");
        }
    
        // 2. Validar campos del DTO
        if (dto.getQuantity() <= 0) {
            return new responseDTO(HttpStatus.BAD_REQUEST.toString(), "La cantidad debe ser mayor a 0");
        }
    
        // 3. Actualizar los campos del pedido
        OrderDrink existingOrderDrink = orderDrinkOpt.get();
        existingOrderDrink.setQuantity(dto.getQuantity());
        // Si hay más campos (ej: drink, order), actualízalos aquí
    
        // 4. Guardar cambios
        data.save(existingOrderDrink);
    
        return new responseDTO(HttpStatus.OK.toString(), "Pedido de bebida actualizado correctamente");
    }

// En OrderDrinkService.java
public List<OrderDrink> filterOrderDrinks(Integer quantity, Integer drinkId, Integer orderId) {
    return data.filterOrderDrinks(quantity, drinkId, orderId);
}

    // Método para convertir un modelo a un DTO
    public OrderDrinkDTO convertToDTO(OrderDrink orderDrink) {
        return new OrderDrinkDTO(orderDrink.getQuantity());
    }

    // Método para convertir un DTO a un modelo
    public OrderDrink convertToModel(OrderDrinkDTO orderDrinkDTO) {
        return new OrderDrink(0, null, null, orderDrinkDTO.getQuantity());
    }
}
