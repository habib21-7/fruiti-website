package com.example.webservices.order_management.enums;
import org.springframework.security.core.GrantedAuthority;

public enum UserRole implements GrantedAuthority {
    CUSTOMER;

    @Override
    public String getAuthority() {
        return name();
    }
}