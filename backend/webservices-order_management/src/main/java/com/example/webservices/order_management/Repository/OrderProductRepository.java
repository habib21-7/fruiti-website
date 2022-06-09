package com.example.webservices.order_management.Repository;

import com.example.webservices.order_management.Model.Customer;
import com.example.webservices.order_management.Model.Order;
import com.example.webservices.order_management.Model.OrderProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderProductRepository extends JpaRepository<OrderProduct,Long> {
}
