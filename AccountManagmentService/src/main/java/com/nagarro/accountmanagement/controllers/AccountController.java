package com.nagarro.accountmanagement.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.nagarro.accountmanagement.model.Account;
import com.nagarro.accountmanagement.model.AccountType;
import com.nagarro.accountmanagement.service.AccountService;

@RestController
@RequestMapping("account-management/api/v1/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;
    
 
    
    @PostMapping
    public ResponseEntity<Account> addAccount(@RequestParam("customerId") String customerId, @RequestParam("accountType") AccountType accountType) {
        Account account = new Account();
        account.setAccountHolderId(customerId);
        account.setType(accountType);
        Account addedAccount = accountService.addAccount(account);
        if (addedAccount != null) {
            return new ResponseEntity<>(addedAccount, HttpStatus.CREATED);
        } else {
            // Account creation failed, you may want to handle this case accordingly
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @PostMapping("/{accountId}/add-money/{customerId}")
    public ResponseEntity<Account> addMoney(@PathVariable String accountId, @RequestBody Double amount, @PathVariable String customerId) {
        Account result = accountService.addMoney(accountId, amount, customerId);
        if (result != null) {
            return new ResponseEntity<>(result, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/{accountId}/withdraw-money/{customerId}")
    public ResponseEntity<Account> withdrawMoney(@PathVariable String accountId, @RequestBody Double amount, @PathVariable String customerId) {
        Account result = accountService.withdrawMoney(accountId, amount, customerId);
        if (result != null) {
            return new ResponseEntity<>(result, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{accountId}")
    public ResponseEntity<Account> getAccountDetails(@PathVariable String accountId) {
           
    	Account account = accountService.getAccountDetails(accountId);
    	if (account != null) {
            return new ResponseEntity<>(account, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }    
    }

    @DeleteMapping("/{accountHolderId}")
    public ResponseEntity<Void> deleteAccount(@PathVariable String accountHolderId) {
    	 accountService.deleteAccountByAccountHolderId(accountHolderId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
