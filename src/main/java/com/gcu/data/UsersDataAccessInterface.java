package com.gcu.data;

import java.util.List;

import com.gcu.data.entity.UserEntity;

public interface UsersDataAccessInterface<T> {
	public List<UserEntity> findAll();
	public boolean verify(String email, String password);
	public T findById(Long id);
	public boolean create(T t);
	public boolean delete(T t);
}
