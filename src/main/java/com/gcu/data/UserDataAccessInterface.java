package com.gcu.data;

import java.util.List;

import com.gcu.data.entity.UserEntity;

public interface UserDataAccessInterface<T> {

	public int verifyLogin(String email, String password);

	public int createAccount(T t);

	public boolean userExists(String username, String email);
	
	public boolean emailExists(String email);
	
	public boolean usernameExists(String username);
	
	public List<UserEntity> getFollowers(Long userId);
}
