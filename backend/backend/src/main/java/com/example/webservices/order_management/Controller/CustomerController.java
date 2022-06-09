package com.example.webservices.order_management.Controller;


import com.example.webservices.order_management.Configs.JwtTokenUtil;
import com.example.webservices.order_management.Dto.CustomerDTO;
import com.example.webservices.order_management.Model.Customer;
import com.example.webservices.order_management.Model.LoginRequest;
import com.example.webservices.order_management.Model.LoginResponse;
import com.example.webservices.order_management.Services.serviceImpl.CustomerServices;
import com.example.webservices.order_management.Services.serviceImpl.LoginUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("customer")
public class CustomerController {
    @Autowired
    private CustomerServices customerServices;

    @Autowired
    private PasswordEncoder bcryptEncoder;

    @Autowired
    LoginUserDetailsService userDetailsService;

    @Autowired
    JwtTokenUtil tokenService;

    @Autowired private AuthenticationManager authenticationManager;

    @GetMapping("")
    public List<Customer> getCustomers(){
        return customerServices.getAllCustomers();
    }

    @GetMapping("/{id}")
    public Optional<Customer> getCustomerById(@PathVariable(name = "id") long id){
        return customerServices.getCustomerById(id);
    }


    @PostMapping("/signup")
    public ResponseEntity<Customer> Register(@RequestBody CustomerDTO customerDTO) throws Exception {
        Customer customer = new Customer();
        customer.setUsername(customerDTO.getUsername());
        customer.setFirst_name(customerDTO.getFirst_name());
        customer.setLast_name(customerDTO.getLast_name());
        customer.setBornAt(customerDTO.getBornAt());
        customer.setPassword(bcryptEncoder.encode(customerDTO.getPassword()));
        return customerServices.save(customer);
    }

    @PostMapping(value = "/login",  produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody LoginRequest authenticationRequest) throws Exception {

        authenticate(authenticationRequest.getUsername(),  authenticationRequest.getPassword());
        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(authenticationRequest.getUsername());
        final String token = tokenService.generateToken(userDetails);
        return  ResponseEntity.ok((new LoginResponse(token)));
    }

    //authenticate user via username(Email) and password using authentication manager
    private void authenticate(String username, String password) throws BadCredentialsException {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerDTO> updateCustomer
            (@Valid @RequestBody CustomerDTO customerDTO
                    , @PathVariable(name = "id") long id) {
        return new ResponseEntity<>(customerServices.updateCustomer(customerDTO, id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCustomer(@PathVariable(name = "id") long id) {
        customerServices.deleteCustomerById(id);
        return new ResponseEntity<>("Deleted successfully.", HttpStatus.OK);
    }
}
