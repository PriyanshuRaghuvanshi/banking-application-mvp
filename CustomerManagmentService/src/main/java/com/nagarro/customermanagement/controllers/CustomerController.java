package com.nagarro.customermanagement.controllers;

import javax.validation.Valid;
import org.springframework.validation.BindingResult;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nagarro.customermanagement.exceptions.ResourceNotFoundException;
import com.nagarro.customermanagement.model.Customer;
import com.nagarro.customermanagement.service.CustomerService;

@RestController
@RequestMapping("/customer-management/api/v1/customers")
public class CustomerController {

	@Autowired
	private CustomerService customerService;
	
	

	@PostMapping
	public ResponseEntity<?> addCustomer(@Valid @RequestBody Customer customer,BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
            // If there are validation errors, return a BAD_REQUEST response with the error details
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Validation failed: " + bindingResult.getAllErrors());
        }
		try {
		Customer addedCustomer = customerService.addCustomer(customer);       
            return new ResponseEntity<>(addedCustomer, HttpStatus.CREATED);
		}catch (HttpMessageNotReadableException ex) {
			throw new ResourceNotFoundException("Invalid customer data provided");
        }
    }
	
    @GetMapping
    public ResponseEntity<List<Customer>> getAllCustomers() {
        return new ResponseEntity<>(customerService.getAllCustomers(), HttpStatus.OK);
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable String customerId) {
        Customer customer = customerService.getCustomerById(customerId);
        if (customer != null) {
            return new ResponseEntity<>(customer, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{customerId}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable String customerId, @RequestBody Customer updatedCustomer) {
        Customer updated = customerService.updateCustomer(customerId, updatedCustomer);
        if (updated != null) {
            return new ResponseEntity<>(updated, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{customerId}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable String customerId) {
        customerService.deleteCustomer(customerId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
    @GetMapping("/validate/{customerId}")
	boolean isValidCustomer(@PathVariable("customerId")String customerId) {
      return customerService.findById(customerId);	 	
	}
    
}

