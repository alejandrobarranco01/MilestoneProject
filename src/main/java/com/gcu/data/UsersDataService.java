package com.gcu.data;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.gcu.data.entity.UserEntity;
import com.gcu.data.repository.UsersRepository;

@Service
public class UsersDataService implements UsersDataAccessInterface<UserEntity> {
	@Autowired
	private UsersRepository usersRepository;
	@Autowired
	private DataSource dataSource;
	@Autowired
	private JdbcTemplate jdbcTemplateObject;
	
	public UsersDataService(UsersRepository usersRepository, DataSource dataSource) {
		this.usersRepository = usersRepository;
		this.dataSource = dataSource;
		this.jdbcTemplateObject = new JdbcTemplate(dataSource);
	}

	@Override
	public List<UserEntity> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserEntity findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean create(UserEntity t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(UserEntity t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean verify(String email, String password) {
	    // Write a SQL query to check if the provided username and password exist in the database
	    String sql = "SELECT COUNT(*) FROM USERS WHERE EMAIL = ? AND PASSWORD = ?";

	    // Use JdbcTemplate to execute the query
	    int count = jdbcTemplateObject.queryForObject(sql, Integer.class, email, password);
	    
	    
	    // If count is greater than 0, it means a matching user was found
	    return count > 0;
	}


}
