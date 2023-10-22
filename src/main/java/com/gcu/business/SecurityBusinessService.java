package com.gcu.business;

import org.springframework.stereotype.Service;

@Service
public class SecurityBusinessService {
	public boolean authenticate(String username, String password) {
		// Incorporate business logic to verify information with database
		
		return true;
	}
	public boolean createAccount(String email, String username, String password) {
		// Incorporate business logic to create account send information to database
		
		return true;
	}
}
