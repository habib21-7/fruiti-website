package com.example.webservices.order_management.Dto;

import com.example.webservices.order_management.Model.Customer;
import lombok.Data;

import java.util.Date;

@Data
public class OrderDTO {

    private int orderId;
    private Date orderAt;

}
