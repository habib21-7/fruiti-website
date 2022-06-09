package com.example.webservices.order_management.Controller;


import com.example.webservices.order_management.Dto.OrderProductDTO;
import com.example.webservices.order_management.Model.Order;
import com.example.webservices.order_management.Model.OrderProduct;
import com.example.webservices.order_management.Services.serviceImpl.OrderProductServices;
import com.example.webservices.order_management.Services.serviceImpl.OrderServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/orderProduct")
public class OrderProductController {
    @Autowired
    OrderProductServices orderProductServices ;


    @PostMapping("")
    public ResponseEntity<OrderProductDTO> createProductOrder(@RequestBody OrderProductDTO orderProductDTO) {
        return orderProductServices.addOrderProduct(orderProductDTO);
    }

}
