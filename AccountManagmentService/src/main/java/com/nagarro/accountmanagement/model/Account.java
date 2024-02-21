package com.nagarro.accountmanagement.model;


import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.Transient;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="account_details")
public class Account {
	
	@Id
    private String accountNumber;
	@Enumerated(EnumType.STRING)
    private AccountType type;
    @Builder.Default
    private Double  balance = 0.0;
    private String accountHolderId;
    
    @Transient
    private Customer customer;
    
    public Account( String accountHolderId, AccountType type,String accountNumber){
        this.accountNumber =accountNumber;
       
        this.accountHolderId = accountHolderId;
        this.type = type;
    }


}
