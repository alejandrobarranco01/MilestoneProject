package com.gcu.data.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "USERS")
public class UserEntity {
	@Id
	@Column(name = "ID")
	Long id;
	@Column(name = "USERNAME")
	String username;
	@Column(name = "EMAIL")
	String email;
	@Column(name = "PASSWORD")
	String password;
}
