package com.nagarro.accountmanagement.service;

public interface CustomerValidationService {

	 boolean doesAccountBelongToCustomer(String accountHolderId, String accountNumber);
}
