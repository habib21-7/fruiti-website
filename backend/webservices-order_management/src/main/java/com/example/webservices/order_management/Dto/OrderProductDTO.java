package com.example.webservices.order_management.Dto;

import com.example.webservices.order_management.Model.Order;
import com.example.webservices.order_management.Model.Product;
import com.example.webservices.order_management.Model.ProductOrderPK;
import lombok.*;

import javax.persistence.*;
@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OrderProductDTO {


    private ProductOrderPK id;
    private Long order_id;
    private Long product_id;
    private Integer quantity;
    private Double price;
    private Integer vat;
}
