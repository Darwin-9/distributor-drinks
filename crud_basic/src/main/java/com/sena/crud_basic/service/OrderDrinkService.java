package com.sena.crud_basic.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.sena.crud_basic.DTO.OrderDrinkDTO;
import com.sena.crud_basic.DTO.responseDTO;
import com.sena.crud_basic.model.OrderDrink;
import com.sena.crud_basic.repository.IOrderDrink;

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

    // Método para convertir un modelo a un DTO
    public OrderDrinkDTO convertToDTO(OrderDrink orderDrink) {
        return new OrderDrinkDTO(orderDrink.getQuantity());
    }

    // Método para convertir un DTO a un modelo
    public OrderDrink convertToModel(OrderDrinkDTO orderDrinkDTO) {
        return new OrderDrink(0, null, null, orderDrinkDTO.getQuantity());
    }
}
