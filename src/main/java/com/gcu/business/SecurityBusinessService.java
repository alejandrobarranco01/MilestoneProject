package com.gcu.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gcu.data.UsersDataService;

/**
 * This class should authenticate and create accounts
 */
@Service
public class SecurityBusinessService implements SecurityBusinessServiceInterface{
	@Autowired
	UsersDataService service;
	
	public boolean authenticate(String email, String password) {
		return service.verify(email, password);
	}

	public boolean createAccount(String email, String username, String password) {
		// Incorporate business logic to create account send information to database
		System.out.println("test from SecurityBusiness: " + email);
		return true;
	}
}
