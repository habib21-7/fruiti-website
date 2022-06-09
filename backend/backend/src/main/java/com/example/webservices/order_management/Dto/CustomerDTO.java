package com.example.webservices.order_management.Dto;

import com.example.webservices.order_management.enums.UserRole;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class CustomerDTO {

    private Long customer_id;
    private String first_name;
    private String last_name;
    private  String username;
    private String password;
    private Date bornAt;
    private UserRole role;

}
