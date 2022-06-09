package com.example.webservices.order_management.Model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name= "product")
public class Product {

    @Id
    @GeneratedValue
    private Long productId;
    private String slug;
    private String name;
    private String reference;
    private int price;
    private int vat;
    private boolean stockable;

    @JsonIgnore

    @OneToMany(mappedBy = "product", fetch=FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderProduct> productOrders = new ArrayList<>();

}
