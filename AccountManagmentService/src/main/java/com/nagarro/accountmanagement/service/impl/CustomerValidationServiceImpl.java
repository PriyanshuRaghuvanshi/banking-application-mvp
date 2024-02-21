package com.nagarro.accountmanagement.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.nagarro.accountmanagement.external.services.CustomerService;
import com.nagarro.accountmanagement.model.Account;
import com.nagarro.accountmanagement.repository.AccountRepository;
import com.nagarro.accountmanagement.service.CustomerValidationService;
import com.nagarro.accountmanagement.exceptions.ResourceNotFoundException;

import org.springframework.stereotype.Service;

@Service
public class CustomerValidationServiceImpl implements CustomerValidationService {
	
	@Autowired
    private AccountRepository accountRepository;
	
	@Autowired
    private  CustomerService customerService;
	
	@Override
	public boolean doesAccountBelongToCustomer(String accountHolderId, String accountNumber) {
    try{
		if(customerService.isValidCustomer(accountHolderId)) {
		Account account = accountRepository.findByAccountNumber(accountNumber);       
        return account != null && account.getAccountHolderId().contains(accountHolderId);
        }else {
		throw new ResourceNotFoundException(" Account Holder Not Found");
  }
    }catch(Exception ex) {
    	 throw new ResourceNotFoundException("Invalid Account holder ID or Account number");
    }
}
}
