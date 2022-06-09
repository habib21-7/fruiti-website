package com.example.webservices.order_management.Controller;


import com.example.webservices.order_management.Dto.CustomerDTO;
import com.example.webservices.order_management.Dto.OrderDTO;
import com.example.webservices.order_management.Model.Customer;
import com.example.webservices.order_management.Model.Order;
import com.example.webservices.order_management.Services.serviceImpl.CustomerServices;
import com.example.webservices.order_management.Services.serviceImpl.OrderServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("order")
public class OrderController {

    @Autowired
    private OrderServices orderServices;

    @GetMapping("")
    public List<Order> getOrders(){
        return orderServices.getAllOrders();
    }

    @GetMapping("/{id}")
    public Optional<Order> getOrderById(@PathVariable(name = "id") long id){
        return orderServices.getOrderById(id);
    }


    @PostMapping("")
    public ResponseEntity<Order> create(@RequestBody Order order) throws URISyntaxException {

        Order result = orderServices.save(order);
        return ResponseEntity.created(new URI("/api/order/" + result.getOrder_id())).body(result);

    }



    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteOrder(@PathVariable(name = "id") long id) {
        orderServices.deleteOrderById(id);
        return new ResponseEntity<>("Deleted successfully.", HttpStatus.OK);
    }
}
