package com.nagarro.accountmanagement.external.services;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import com.nagarro.accountmanagement.model.Customer;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="CUSTOMER-MANAGEMENT-SERVICE")
public interface CustomerService {

	@GetMapping("/customer-management/api/v1/customers/{accountHolderId}")
	Customer getCustomer(@PathVariable("accountHolderId") String accountHolderId);
	
	@GetMapping("/customer-management/api/v1/customers/validate/{accountHolderId}")
	boolean isValidCustomer(@PathVariable("accountHolderId")String accountHolderId);
}
