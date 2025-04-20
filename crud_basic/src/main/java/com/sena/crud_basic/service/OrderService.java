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


  // Eliminar (lógica)
  public responseDTO delete(int id) {
    Optional<Order> order = findById(id);
    if (!order.isPresent()) {
        return new responseDTO(HttpStatus.BAD_REQUEST.toString(), "La orden no existe");
    }

    order.get().setStatus(false);
    data.save(order.get());

    return new responseDTO(HttpStatus.OK.toString(), "Orden eliminada correctamente");
}


 // Actualizar orden
 public responseDTO updateOrder(int id, OrderDTO dto) {
    Optional<Order> orderOpt = data.findById(id);
    if (!orderOpt.isPresent()) {
        return new responseDTO(HttpStatus.NOT_FOUND.toString(), "La orden con ID " + id + " no existe");
    }

    if (dto.getOrder_date() == null) {
        return new responseDTO(HttpStatus.BAD_REQUEST.toString(), "La fecha del pedido no puede ser nula");
    }

    Order order = orderOpt.get();
    order.setOrder_date(dto.getOrder_date());

    data.save(order);

    return new responseDTO(HttpStatus.OK.toString(), "Orden actualizada correctamente");
}


 // Filtro por fecha y status
 public List<Order> filterOrders(String order_date, Boolean status) {
    return data.filterOrders(order_date, status);
}



    // Método para convertir un modelo a un DTO
    public OrderDTO convertToDTO(Order order) {
        return new OrderDTO(order.getOrder_date(), true);
    }

    // Método para convertir un DTO a un modelo
    public Order convertToModel(OrderDTO orderDTO) {
        return new Order(0, null, orderDTO.getOrder_date(), true);
    }
}
