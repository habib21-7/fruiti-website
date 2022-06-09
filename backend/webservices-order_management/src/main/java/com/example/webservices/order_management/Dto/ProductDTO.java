package com.example.webservices.order_management.Dto;

import lombok.Data;

@Data
public class ProductDTO {

    private Long productId;
    private String name;
    private String reference;
    private String slug;
    private int vat;
    private int price;
    private boolean stockable;

}
