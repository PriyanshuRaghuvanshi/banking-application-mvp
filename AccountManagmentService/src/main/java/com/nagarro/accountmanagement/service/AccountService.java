package com.nagarro.accountmanagement.service;

import com.nagarro.accountmanagement.model.Account;

public interface AccountService {

	    Account addMoney(String accountId, Double amount, String customerId);
	    Account withdrawMoney(String accountId, Double amount, String customerId);
	    Account getAccountDetails(String accountId);
	    void deleteAccount(String accountHolderId);
		Account addAccount(Account account);
		void deleteAccountByAccountHolderId(String accountHolderId);
}
