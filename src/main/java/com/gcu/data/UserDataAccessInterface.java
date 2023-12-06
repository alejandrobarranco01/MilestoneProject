package com.gcu.data;

import java.util.List;

import com.gcu.data.entity.UserEntity;

public interface UserDataAccessInterface<T> {

	public boolean verifyLogin(String email, String password);

	public boolean createAccount(T t);

	public boolean userExists(String username, String email);
	
	public List<UserEntity> getFollowers(Long userId);
}
