package com.example.webservices.order_management.Controller;


import com.example.webservices.order_management.Dto.CustomerDTO;
import com.example.webservices.order_management.Dto.ProductDTO;
import com.example.webservices.order_management.Model.Customer;
import com.example.webservices.order_management.Model.Product;
import com.example.webservices.order_management.Services.serviceImpl.CustomerServices;
import com.example.webservices.order_management.Services.serviceImpl.ProductServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("product")
public class ProductController {

    @Autowired
    private ProductServices productServices;

    @GetMapping("")
    public List<Product> getProducts(){
        return productServices.getAllProducts();
    }


    @PostMapping("")
    public ResponseEntity<Product> create(@RequestBody Product product) throws URISyntaxException {

        Product result = productServices.save(product);
        return ResponseEntity.created(new URI("/api/product/" + result.getProductId())).body(result);

    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDTO> updateProduct
            (@Valid @RequestBody ProductDTO productDTO
                    , @PathVariable(name = "id") long id) {
        return new ResponseEntity<>(productServices.updateProduct(productDTO, id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable(name = "id") long id) {
        productServices.deleteProductById(id);
        return new ResponseEntity<>("Deleted successfully.", HttpStatus.OK);
    }
}
