package com.nagarro.customermanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nagarro.customermanagement.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, String> {

	
    
}