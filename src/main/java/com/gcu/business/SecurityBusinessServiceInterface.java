package com.gcu.business;

import java.util.List;

import com.gcu.model.UserModel;

public interface SecurityBusinessServiceInterface {

	public int authenticate(String username, String password);

	public int createAccount(String email, String username, String password);

	public List<UserModel> getFollowers(Long userId);

	public List<UserModel> getAllUsers();

	public UserModel getUser(Long userId);
}
