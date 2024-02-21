package com.nagarro.customermanagement.external.services;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.nagarro.customermanagement.model.Account;
import com.nagarro.customermanagement.model.AccountType;

@FeignClient(name="ACCOUNT-MANAGEMENT-SERVICE")
public interface AccountService {

	@PostMapping("/account-management/api/v1/accounts")
	Account addAccount(@RequestParam("customerId") String customerId, @RequestParam("accountType") AccountType accountType);
	
	@DeleteMapping("/account-management/api/v1/accounts/{accountHolderId}")
	void deleteAccount(@PathVariable String accountHolderId);
	
}
