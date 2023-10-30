/**
 * Represents the data model for user registration functionality.
 */
package com.gcu.model;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 * Data model class for user registration information.
 */
public class RegisterModel {
	@Id
	@GeneratedValue
	private Long id;
    /**
     * User's email address for registration.
     */
    @NotNull(message = "Email is a required field")
    @Size(min = 1, max = 64, message = "Email must be between 1 and 64 characters")
    private String email;

    /**
     * User's chosen username for registration.
     */
    @NotNull(message = "Username is a required field")
    @Size(min = 1, max = 32, message = "Username must be between 1 and 32 characters")
    private String username;

    /**
     * User's chosen password for registration.
     */
    @NotNull(message = "Password is a required field")
    @Size(min = 1, max = 32, message = "Password must be between 1 and 32 characters")
    private String password;

    /**
     * User's confirmation of the chosen password for registration.
     */
    @NotNull(message = "Confirm password is a required field")
    @Size(min = 1, max = 32, message = "Confirm password must be between 1 and 32 characters")
    private String confirmPassword;

    /**
     * Gets the user's email address.
     *
     * @return String representing the user's email address.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the user's email address.
     *
     * @param email String representing the user's email address.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets the user's chosen username.
     *
     * @return String representing the user's chosen username.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the user's chosen username.
     *
     * @param username String representing the user's chosen username.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Gets the user's chosen password.
     *
     * @return String representing the user's chosen password.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the user's chosen password.
     *
     * @param password String representing the user's chosen password.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Gets the user's confirmation of the chosen password.
     *
     * @return String representing the user's confirmation of the chosen password.
     */
    public String getConfirmPassword() {
        return confirmPassword;
    }

    /**
     * Sets the user's confirmation of the chosen password.
     *
     * @param confirmPassword String representing the user's confirmation of the chosen password.
     */
    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    /**
     * Validates whether the entered password matches the confirmed password.
     *
     * @return true if the passwords match, false otherwise.
     */
    @AssertTrue(message = "Passwords don't match")
    private boolean isPasswordMatch() {
        return password != null && password.equals(confirmPassword);
    }
}
