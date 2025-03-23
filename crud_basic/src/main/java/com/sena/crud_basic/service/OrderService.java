package com.sena.crud_basic.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.sena.crud_basic.DTO.OrderDTO;
import com.sena.crud_basic.DTO.responseDTO;
import com.sena.crud_basic.model.Order;
import com.sena.crud_basic.repository.IOrder;

@Service
public class OrderService {

    @Autowired
    private IOrder data;

    // Método para guardar un pedido con validaciones
    public responseDTO save(OrderDTO orderDTO) {
        if (orderDTO.getStatus().length() < 3 || orderDTO.getStatus().length() > 20) {
            return new responseDTO(HttpStatus.BAD_REQUEST.toString(), "El estado debe tener entre 3 y 20 caracteres");
        }

        Order order = convertToModel(orderDTO);
        data.save(order);

        return new responseDTO(HttpStatus.OK.toString(), "Pedido guardado exitosamente");
    }

    // Método para obtener todos los pedidos
    public List<Order> findAll() {
        return data.findAll();
    }

    // Método para buscar un pedido por ID
    public Optional<Order> findById(int id) {
        return data.findById(id);
    }

    // Método para eliminar un pedido por ID
    public responseDTO deleteUser(int id) {
        Optional<Order> order = findById(id);
        if (!order.isPresent()) {
            return new responseDTO(HttpStatus.BAD_REQUEST.toString(), "El pedido no existe");
        }
        data.deleteById(id);
        return new responseDTO(HttpStatus.OK.toString(), "Pedido eliminado correctamente");
    }

    // Método para convertir un modelo a un DTO
    public OrderDTO convertToDTO(Order order) {
        return new OrderDTO(order.getOrder_date(), order.getStatus());
    }

    // Método para convertir un DTO a un modelo
    public Order convertToModel(OrderDTO orderDTO) {
        return new Order(0, null, orderDTO.getOrder_date(), orderDTO.getStatus());
    }
}
