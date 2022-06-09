package com.example.webservices.order_management.Services.serviceImpl;


import com.example.webservices.order_management.Dto.CustomerDTO;
import com.example.webservices.order_management.Dto.OrderDTO;
import com.example.webservices.order_management.Exeptions.ResourceNotFoundException;
import com.example.webservices.order_management.Model.Customer;
import com.example.webservices.order_management.Model.Order;
import com.example.webservices.order_management.Repository.CustomerRepository;
import com.example.webservices.order_management.Repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderServices {

    @Autowired
    private OrderRepository orderRepository;


    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Optional<Order> getOrderById(long id) {
        return orderRepository.findById(id);
    }

    public Order save(Order order) {

        return orderRepository.save(order);
    }


    public void deleteOrderById(long id) {
        Order order = orderRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("order", "id", id));
        orderRepository.delete(order);
    }

}
