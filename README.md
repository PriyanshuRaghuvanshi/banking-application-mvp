# banking-application-mvp
Introduction
The assignment focuses on building a Minimum Viable Product (MVP) for a banking application using Microservices architecture. The solution consists of two main microservices: Customer Management Service and Account Management Service. Additionally, components like API Gateway, Eureka server for service registration, and centralized configuration management are required.

Microservices Description
Customer Management Service:
- Endpoints:
  - Add customer
  - Get all Customers
  - Get single Customer Details
  - Update Customer Details
  - Delete Customer (Also deletes the customer account from the Account Management Service)
  
Account Management Service:
- Endpoints:
  - Add Money to account (Validates customer details before adding money)
  - Withdraw money from account (Validates customer details before withdrawing money)
  - Get Account details (Includes account details and related customer details)
  - Delete Account

Other Components
- API Gateway:  Facilitates communication between clients and microservices.
- Eureka Server:  Service registration and discovery for microservices.
- Centralized Configuration Management:  Manages configurations for all microservices centrally.


Running the Microservices

1. API Gateway:  Run the API Gateway service.
   - Sample URL: http://localhost:8085/

2. Eureka Server:
   - Run the Eureka server for service registration.
   - Sample URL: http://localhost:8083/

3. Configuration Service:
Features:
- Runs on port 8086.
- Retrieves configurations from GitHub repository(https://github.com/PriyanshuRaghuvanshi/banking-application-config)
- Provides centralized configuration management for microservices.

4. Customer Management Service (also accessible using port 8085/ApiGateway)

   - Run the Customer Management Service.
   - Sample URLs:
     - Add customer: 
http://localhost:8082/customer-management/api/v1/customers  
   - Get all Customers: 
http://localhost:8082/customer-management/api/v1/customers
     - Get single Customer Details: http://localhost:8082/customer-management/api/v1/customers/{customerId}
     - Update Customer Details: http://localhost:8082/customer-management/api/v1/customers/{customerId}
     - Delete Customer: http://localhost:8085/customer-management/api/v1/customers/{customerId}

5. Account Management Service: (also accessible using port 8085/ApiGateway)
  
 - Run the Account Management Service.
   - Sample URLs:
     - Add Money to account: http://localhost:8084/account-management/api/v1/accounts/{accountId}/add-money/{customerId}    
 - Withdraw money from account: http://localhost:8084/account-management/api/v1/accounts/{accountId}/withdraw-money/{customerId}
     - Get Account details: http://localhost:8084/account-management/api/v1/accounts/{accountId}
     - Delete Account: http://localhost:8084/account-management/api/v1/accounts/{accountId}

Assumptions

•	Database Integration: MySQL database is utilized for both Customer and Account services.
•	Account Creation: Account creation occurs at the time of user creation to ensure seamless association.
•	Customer Validation: Customer validation in add money and withdraw money operations involves two checks:
1.	Customer existence in the database.
2.	Verification of account ownership by the customer. 

