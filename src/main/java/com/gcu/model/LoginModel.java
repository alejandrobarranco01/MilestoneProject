package com.gcu.model;

/**
 * Data model class for user login information.
 */
public class LoginModel {

    /**
     * User's username for authentication.
     */
    @jakarta.validation.constraints.NotNull(message = "User name is a required field")
    @jakarta.validation.constraints.Size(min = 1, max = 32, message = "User name must be between 1 and 32 characters")
    private String username;

    /**
     * User's password for authentication.
     */
    @jakarta.validation.constraints.NotNull(message = "Password is a required field")
    @jakarta.validation.constraints.Size(min = 1, max = 32, message = "Password must be between 1 and 32 characters")
    private String password;

    /**
     * Gets the user's username.
     *
     * @return String representing the user's username.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the user's username.
     *
     * @param username String representing the user's username.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Gets the user's password.
     *
     * @return String representing the user's password.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the user's password.
     *
     * @param password String representing the user's password.
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
