package com.example.webservices.order_management.Services.serviceImpl;

import com.example.webservices.order_management.Dto.CustomerDTO;
import com.example.webservices.order_management.Exeptions.ResourceNotFoundException;
import com.example.webservices.order_management.Model.Customer;

import com.example.webservices.order_management.Repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;


import java.util.List;

@Service
public class CustomerServices {

    @Autowired
    private CustomerRepository customerRepository;


    public List<Customer> getAllCustomers() {
        return customerRepository.findAllCustomers();
    }

    public Optional<Customer> getCustomerById(long id) {
        return customerRepository.findById(id);
    }
    public ResponseEntity<Customer> save(Customer customer) throws Exception {
        if(customerRepository.findByUsername(customer.getUsername())!=null){
            return new ResponseEntity<>(HttpStatus.CONFLICT);

        }
        return new ResponseEntity<>(customerRepository.save(customer), HttpStatus.OK);
    }


    public CustomerDTO updateCustomer(CustomerDTO customerDTO, long id) {
        Customer customer = customerRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("customer","id",id));
        customer.setFirst_name(customerDTO.getFirst_name());
        customer.setLast_name(customerDTO.getLast_name());
        customer.setBornAt(customerDTO.getBornAt());


        Customer updatedCustomer = customerRepository.save(customer);
        return mapToDTO(updatedCustomer);
    }

    public void deleteCustomerById(long id) {
        Customer customer = customerRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("customer", "id", id));
        customerRepository.delete(customer);
    }

    private CustomerDTO mapToDTO(Customer customer) {
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setFirst_name(customer.getFirst_name());
        customerDTO.setLast_name(customer.getLast_name());
        customerDTO.setBornAt(customer.getBornAt());

        return customerDTO;
    }


}
