package com.nagarro.customermanagement.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.nagarro.customermanagement.exceptions.ResourceNotFoundException;
import com.nagarro.customermanagement.external.services.AccountService;
import com.nagarro.customermanagement.model.AccountType;
import com.nagarro.customermanagement.model.Customer;
import com.nagarro.customermanagement.repository.CustomerRepository;
import com.nagarro.customermanagement.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;
    
    @Autowired
	private AccountService accountService;

    @Override
    public Customer addCustomer(Customer customer) {
    try {
    	String randomUserId =UUID.randomUUID().toString();
    	customer.setId(randomUserId);
    	
    	String customerId = customer.getId();
        AccountType accountType = customer.getAccountType();
    	accountService.addAccount(customerId, accountType);
      return customerRepository.save(customer);
    }catch (IllegalArgumentException  ex) {
        throw new ResourceNotFoundException("Invalid customer data provided");
    }
    }

    @Override
    public List<Customer> getAllCustomers() {
      try {
    	return customerRepository.findAll();
      } catch (Exception ex) {
          throw new ResourceNotFoundException("No customers available");
      }
    }

    @Override
    public Customer getCustomerById(String customerId) {
        return customerRepository.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("customer with given id is not found on server !! :"+customerId));
    }

    @Override
    public Customer updateCustomer(String customerId, Customer updatedCustomer) {
    try {	
        Optional<Customer> existingCustomerOptional = customerRepository.findById(customerId);

        if (existingCustomerOptional.isPresent()) {
            Customer existingCustomer = existingCustomerOptional.get();
            existingCustomer.setFirstName(updatedCustomer.getFirstName());
            existingCustomer.setLastName(updatedCustomer.getLastName());
            existingCustomer.setEmail(updatedCustomer.getEmail());
            existingCustomer.setAddress(updatedCustomer.getAddress());
            return customerRepository.save(existingCustomer);
        }else {
                  throw new ResourceNotFoundException("Customer with ID: " + customerId + " not found");
    }
    } catch (Exception ex) {
        throw new ResourceNotFoundException("Error while Updating the customer with ID"+ customerId);
    }
    }

    @Override
    public void deleteCustomer(String customerId) {
    	 try {
        customerRepository.deleteById(customerId);
        accountService.deleteAccount(customerId);
    	 }catch (Exception ex) {
             throw new ResourceNotFoundException("Error while deleting the customer with ID"+ customerId);
         }
    }
    

	@Override
	public boolean findById(String customerId) {
		
        Optional<Customer> customer = customerRepository.findById(customerId);
        return customer != null;
		}
	 
}