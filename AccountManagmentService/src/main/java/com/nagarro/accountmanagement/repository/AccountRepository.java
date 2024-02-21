package com.nagarro.accountmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nagarro.accountmanagement.model.Account;

public interface AccountRepository extends JpaRepository<Account, String>{

	void deleteByAccountHolderId(String accountHolderId);

	Account findByAccountNumber(String accountNumber);

}
