package com.nagarro.accountmanagement.model;



import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;




@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="customers")
public class Customer {
    
	@Id
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String address;
    private AccountType accountType;
     
}
   

