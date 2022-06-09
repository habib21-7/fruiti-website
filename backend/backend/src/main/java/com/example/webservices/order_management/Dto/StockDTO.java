package com.example.webservices.order_management.Dto;

import lombok.Data;

import java.util.Date;

@Data
public class StockDTO {

    private Long stockId;
    private int quantity ;
    private Date updateAt;

}
