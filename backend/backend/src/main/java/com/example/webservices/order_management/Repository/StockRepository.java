package com.example.webservices.order_management.Repository;

import com.example.webservices.order_management.Model.Product;
import com.example.webservices.order_management.Model.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StockRepository extends JpaRepository<Stock,Long> {

    @Query(value = "SELECT c.* FROM stock as c", nativeQuery = true)
    List<Stock> findAllStocks();
}
