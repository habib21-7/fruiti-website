package com.example.webservices.order_management.Services.serviceImpl;

import com.example.webservices.order_management.Dto.OrderProductDTO;
import com.example.webservices.order_management.Model.Order;
import com.example.webservices.order_management.Model.OrderProduct;
import com.example.webservices.order_management.Model.ProductOrderPK;
import com.example.webservices.order_management.Repository.OrderProductRepository;
import com.example.webservices.order_management.Repository.OrderRepository;
import com.example.webservices.order_management.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderProductServices {

    @Autowired
    private OrderProductRepository orderProductRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private OrderRepository orderRepository;



    public ResponseEntity<OrderProductDTO> addOrderProduct(OrderProductDTO orderProductDTO) {
        OrderProduct orderProduct = new OrderProduct();
        orderProduct.setOrder(orderRepository.getById(orderProductDTO.getOrder_id()));
        orderProduct.setProduct(productRepository.getById(orderProductDTO.getProduct_id()));
        orderProduct.setId(new ProductOrderPK(orderProduct.getOrder().getOrder_id(),orderProduct.getProduct().getProductId()));
        orderProduct.setPrice(orderProductDTO.getPrice());
        orderProduct.setQuantity(orderProductDTO.getQuantity());
        orderProduct.setVat(orderProductDTO.getVat());

        OrderProduct newProductOrder = orderProductRepository.save(orderProduct);
        OrderProductDTO orderProductDTO1 = new OrderProductDTO();
        orderProductDTO1.setOrder_id(orderProduct.getOrder().getOrder_id());
        orderProductDTO1.setProduct_id(orderProduct.getProduct().getProductId());
        orderProductDTO1.setPrice(orderProduct.getPrice());
        orderProductDTO1.setQuantity(orderProduct.getQuantity());
        orderProductDTO1.setVat(orderProduct.getVat());
        return new ResponseEntity<OrderProductDTO>(orderProductDTO1, HttpStatus.CREATED) ;

    }


    }
