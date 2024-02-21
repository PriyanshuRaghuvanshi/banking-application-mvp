package com.nagarro.customermanagement.model;


import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
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
	
	 @NotBlank(message = "First name is required")
    private String firstName;
	 @NotBlank(message = "Last name is required")
    private String lastName;
	 @Email(message = "Invalid email address")
    private String email;
	 @NotBlank(message = "Address is required")
    private String address;
    @Enumerated(EnumType.STRING)
    private AccountType accountType;
   
   
}
