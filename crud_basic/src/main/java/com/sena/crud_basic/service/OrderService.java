package com.sena.crud_basic.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.sena.crud_basic.DTO.OrderDTO;

import com.sena.crud_basic.DTO.responseDTO;
import com.sena.crud_basic.model.Order;
import com.sena.crud_basic.model.Store;

import com.sena.crud_basic.repository.IOrder;
import com.sena.crud_basic.repository.IStore;

@Service
public class OrderService {

    @Autowired
    private IOrder data;

    @Autowired
    private IStore storeRepository;

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
    try {
        Optional<Order> orderOpt = data.findById(id);
        
        if (!orderOpt.isPresent()) {
            return new responseDTO(String.valueOf(HttpStatus.NOT_FOUND.value()), "Orden no encontrada");
        }

        Order order = orderOpt.get();
        order.setStatus(false);
        data.save(order);
        
        return new responseDTO(
            String.valueOf(HttpStatus.OK.value()), // Asegúrate de enviar solo el número
            "Orden desactivada correctamente"
        );
        
    } catch (Exception e) {
        return new responseDTO(
            String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()),
            "Error al desactivar orden: " + e.getMessage()
        );
    }
}


 // Actualizar orden
 public responseDTO updateOrder(int id, OrderDTO dto) {
    try {
        Optional<Order> orderOpt = data.findById(id);
        if (!orderOpt.isPresent()) {
            return new responseDTO(String.valueOf(HttpStatus.NOT_FOUND.value()), 
                                "La orden con ID " + id + " no existe");
        }

        if (dto.getOrder_date() == null) {
            return new responseDTO(String.valueOf(HttpStatus.BAD_REQUEST.value()), 
                                "La fecha del pedido no puede ser nula");
        }

        // Obtener la tienda desde la base de datos
        Store store = storeRepository.findById(dto.getStore_id())
                .orElseThrow(() -> new RuntimeException("Tienda no encontrada con ID: " + dto.getStore_id()));

        Order order = orderOpt.get();
        order.setOrder_date(dto.getOrder_date());
        order.setStore(store); // Asegurar que la tienda está actualizada

        data.save(order);

        return new responseDTO(String.valueOf(HttpStatus.OK.value()), 
                            "Orden actualizada correctamente");
    } catch (Exception e) {
        return new responseDTO(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()),
                            "Error al actualizar orden: " + e.getMessage());
    }
}


 // Filtro por fecha y status
 public List<Order> filterOrders(String order_date, Boolean status) {
    return data.filterOrders(order_date, status);
}



    // Método para convertir un modelo a un DTO
    public OrderDTO convertToDTO(Order order) {
        return new OrderDTO(order.getOrder_date(), true, order.getStore().getStore_id());
    }

    // Método para convertir un DTO a un modelo
    public Order convertToModel(OrderDTO orderDTO) {
        // Obtener la tienda desde la base de datos
        Store store = storeRepository.findById(orderDTO.getStore_id())
                .orElseThrow(() -> new RuntimeException("Tienda no encontrada con ID: " + orderDTO.getStore_id()));
        
        return new Order(0, store, orderDTO.getOrder_date(), true);
    }
}
