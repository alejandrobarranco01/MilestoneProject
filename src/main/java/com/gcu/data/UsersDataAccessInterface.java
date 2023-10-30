package com.gcu.data;

import java.util.List;

import com.gcu.data.entity.UserEntity;

public interface UsersDataAccessInterface<T> {
	public List<UserEntity> findAll();
	public T findById(Long id);
	public boolean create(T t);
	public boolean delete(T t);
}
