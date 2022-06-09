package com.example.webservices.order_management.Services.serviceImpl;


import com.example.webservices.order_management.Dto.CustomerDTO;
import com.example.webservices.order_management.Dto.ProductDTO;
import com.example.webservices.order_management.Exeptions.ResourceNotFoundException;
import com.example.webservices.order_management.Model.Customer;
import com.example.webservices.order_management.Model.Product;
import com.example.webservices.order_management.Repository.CustomerRepository;
import com.example.webservices.order_management.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServices {
    @Autowired
    private ProductRepository  productRepository;

    public List<Product> getAllProducts() {

        return productRepository.findAllProducts();
    }


    public Product save(Product product) {

        return productRepository.save(product);
    }


    public ProductDTO updateProduct(ProductDTO productDTO, long id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("product","id",id));
        product.setName(productDTO.getName());
        product.setPrice(productDTO.getPrice());
        product.setSlug(productDTO.getSlug());
        product.setReference(productDTO.getReference());
        product.setVat(productDTO.getVat());


        Product updatedProduct = productRepository.save(product);
        return mapToDTO(updatedProduct);
    }

    public void deleteProductById(long id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("product", "id", id));
        productRepository.delete(product);
    }

    private ProductDTO mapToDTO(Product product) {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setName(productDTO.getName());
        productDTO.setPrice(productDTO.getPrice());
        productDTO.setSlug(productDTO.getSlug());
        productDTO.setReference(productDTO.getReference());
        productDTO.setVat(productDTO.getVat());
        return productDTO;
    }



}
