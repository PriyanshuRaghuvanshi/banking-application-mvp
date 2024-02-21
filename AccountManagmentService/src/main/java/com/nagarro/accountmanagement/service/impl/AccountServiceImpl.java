package com.nagarro.accountmanagement.service.impl;

import java.util.Optional;
import java.util.UUID;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nagarro.accountmanagement.external.services.CustomerService;
import com.nagarro.accountmanagement.model.Account;
import com.nagarro.accountmanagement.model.Customer;
import com.nagarro.accountmanagement.repository.AccountRepository;
import com.nagarro.accountmanagement.service.AccountService;
import com.nagarro.accountmanagement.exceptions.ResourceNotFoundException;

@Service
public class AccountServiceImpl implements AccountService{
	
	    @Autowired
	    private AccountRepository accountRepository;
	    
	    @Autowired
	    private CustomerService customerService;
	    
	    @Autowired
	    private CustomerValidationServiceImpl customerValidationService;


	@Override
	public Account addMoney(String accountId, Double amount, String customerId) {
		 if (customerValidationService.doesAccountBelongToCustomer(customerId, accountId)) {
	            Account account = getAccountDetails(accountId);
	            if (account != null) {
	                account.setBalance(account.getBalance() + amount);
	                return accountRepository.save(account);
	            }else {
	            	 throw new ResourceNotFoundException("Account not found with ID: " + accountId);
	            }
	        }else {
	        	throw new ResourceNotFoundException("Account does not belong to customer with ID: " + customerId);
	        }
				
	}

	@Override
	public Account withdrawMoney(String accountId, Double amount, String customerId) {
		if (customerValidationService.doesAccountBelongToCustomer(customerId, accountId)) {
            Account account = getAccountDetails(accountId);
            if (account != null && account.getBalance() >= amount) {
                account.setBalance(account.getBalance() - amount);
                return accountRepository.save(account);
            } else {
                throw new ResourceNotFoundException("Insufficient balance in account with ID: " + accountId);
            }
        } else {
            throw new ResourceNotFoundException("Account does not belong to customer with ID: " + customerId);
        }
		}
        

	@Override
	public Account getAccountDetails(String accountId) {
		Optional<Account> accountOptional = accountRepository.findById(accountId);
	    if (accountOptional.isPresent()) {
	        Account account = accountOptional.get();
	        String accountHolderId = account.getAccountHolderId();
	        Customer customer = customerService.getCustomer(accountHolderId);
	        account.setCustomer(customer);
	        return account;
	    } else {
	    	 throw new ResourceNotFoundException("Account not found with ID: " + accountId);
        }
	    }      
	
	@Override
	public void deleteAccount(String accountId) {
		try {
		 accountRepository.deleteByAccountHolderId(accountId);
		}catch (Exception ex) {
	          throw new ResourceNotFoundException("account not found");
	      }
	}

	@Override
	public Account addAccount(Account account) {
		try {
		String accountNumber = UUID.randomUUID().toString();
             account.setAccountNumber(accountNumber);
	      return accountRepository.save(account);
		}catch (Exception ex) {
	          throw new ResourceNotFoundException("Invalid Account");
	      }
	}

	@Override
	@Transactional
	public void deleteAccountByAccountHolderId(String accountHolderId) {
		try { 
		accountRepository.deleteByAccountHolderId(accountHolderId);
		}catch (Exception ex) {
	          throw new ResourceNotFoundException("Unable to delete account");
	      }
	}

	
	

}
