package com.gcu.data;

import java.util.List;

import com.gcu.data.entity.UserEntity;

public interface UsersDataAccessInterface<T> {

	public boolean verifyLogin(String email, String password);

	public boolean createAccount(T t);

}
