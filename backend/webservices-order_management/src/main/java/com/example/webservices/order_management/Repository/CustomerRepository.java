package com.example.webservices.order_management.Repository;

import com.example.webservices.order_management.Model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {


    @Query(value = "SELECT c.* FROM customer as c", nativeQuery = true)
    List<Customer> findAllCustomers();

    Customer findByUsername(String username);
}
