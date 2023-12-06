package com.gcu.data;

public interface UserDataAccessInterface<T> {

	public boolean verifyLogin(String email, String password);

	public boolean createAccount(T t);

	public boolean userExists(String username, String email);
}
