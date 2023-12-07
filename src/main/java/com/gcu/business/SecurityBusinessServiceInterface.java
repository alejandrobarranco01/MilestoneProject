package com.gcu.business;

import java.util.List;

import com.gcu.model.UserModel;

public interface SecurityBusinessServiceInterface {

	public boolean authenticate(String username, String password);

	public int createAccount(String email, String username, String password);
	
	public List<UserModel> getFollowers(Long userId);
}
