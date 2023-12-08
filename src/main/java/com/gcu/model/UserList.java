package com.gcu.model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "users")
public class UserList {
	private List<UserModel> users = new ArrayList<UserModel>();

	@XmlElement(name = "user")
	public List<UserModel> getUsers() {
		return this.users;
	}

	public void setUsers(List<UserModel> users) {
		this.users = users;
	}
}
