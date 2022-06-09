package com.example.webservices.order_management.Services.serviceImpl;
import java.util.*;


import com.example.webservices.order_management.Model.Customer;
import com.example.webservices.order_management.Repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class LoginUserDetailsService implements UserDetailsService {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    //login
    //load user from database
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Customer user = customerRepository.findByUsername(username);
        //if user is deleted, don't allow siging in
        if (user == null ) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        //set authority of logged in here to allow access using preauthorize(hasauthority)
        // enum should extend GrantedAuthoritreis and return it, check UserRole Enum
        return new User(user.getUsername(),user.getPassword(),Arrays.asList(user.getRole()));
    }
}