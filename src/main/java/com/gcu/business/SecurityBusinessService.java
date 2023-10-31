package com.gcu.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gcu.data.UsersDataService;
import com.gcu.data.entity.UserEntity;

/**
 * This class should authenticate and create accounts
 */
@Service
public class SecurityBusinessService implements SecurityBusinessServiceInterface{
	@Autowired
	UsersDataService service;
	
	public boolean authenticate(String email, String password) {
		return service.verifyLogin(email, password);
	}

	public boolean createAccount(String email, String username, String password) {
        // You can validate the inputs here if needed
        UserEntity user = new UserEntity(username, email, password);
        return service.createAccount(user);
    }
}
