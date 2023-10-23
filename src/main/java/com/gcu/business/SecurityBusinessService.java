package com.gcu.business;

import org.springframework.stereotype.Service;

/**
 * This class should authenticate and create accounts
 */
@Service
public class SecurityBusinessService implements SecurityBusinessServiceInterface{
	public boolean authenticate(String username, String password) {
		// Incorporate business logic to verify information with database
		
		return true;
	}

	public boolean createAccount(String email, String username, String password) {
		// Incorporate business logic to create account send information to database
		System.out.println("test from SecurityBusiness: " + email);
		return true;
	}
}
