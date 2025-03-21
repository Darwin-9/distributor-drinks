package com.sena.crud_basic.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sena.crud_basic.DTO.OrderDTO;
import com.sena.crud_basic.model.Order;
import com.sena.crud_basic.repository.IOrder;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private IOrder data;

    public void save(OrderDTO orderDTO) {
        Order order = convertToModel(orderDTO);
        data.save(order);
    }

    public List<Order> findAll() {
        return data.findAll();
    }

    public Optional<Order> findById(int id) {
        return data.findById(id);
    }

    public void delete(int id) {
        data.deleteById(id);
    }

    public OrderDTO convertToDTO(Order order) {
        return new OrderDTO(
                order.getStore(),
                order.getDistributor(),
                order.getOrder_date());
    }

    public Order convertToModel(OrderDTO orderDTO) {
        return new Order(
                0,
                orderDTO.getStore(),
                orderDTO.getDistributor(),
                orderDTO.getOrder_date());
    }
}
