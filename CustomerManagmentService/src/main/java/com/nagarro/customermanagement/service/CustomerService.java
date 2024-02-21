package com.nagarro.customermanagement.service;

import java.util.List;

import com.nagarro.customermanagement.model.Customer;

public interface CustomerService {

	    Customer addCustomer(Customer customer);
	    List<Customer> getAllCustomers();
	    Customer getCustomerById(String customerId);
	    Customer updateCustomer(String customerId, Customer updatedCustomer);
	    void deleteCustomer(String customerId);
	    boolean findById(String customerId);
}
