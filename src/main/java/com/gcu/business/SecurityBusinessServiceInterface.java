package com.gcu.business;

public interface SecurityBusinessServiceInterface {
    public boolean authenticate(String username, String password);
    public boolean createAccount(String email, String username, String password);
}
