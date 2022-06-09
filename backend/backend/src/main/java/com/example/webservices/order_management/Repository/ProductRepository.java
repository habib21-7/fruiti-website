package com.example.webservices.order_management.Repository;

import com.example.webservices.order_management.Model.Customer;
import com.example.webservices.order_management.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {


    @Query(value = "SELECT c.* FROM product as c", nativeQuery = true)
    List<Product> findAllProducts();
}
