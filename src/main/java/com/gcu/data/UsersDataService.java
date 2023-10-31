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
    public boolean createAccount(UserEntity user) {
        String sql = "INSERT INTO USERS (USERNAME, EMAIL, PASSWORD) VALUES (?, ?, ?)";
        int rowsAffected = jdbcTemplateObject.update(sql, user.getUsername(), user.getEmail(), user.getPassword());
        return rowsAffected > 0;
    }

	
	@Override
	public boolean verifyLogin(String email, String password) {
        // If count is greater than 0, it means a matching user was found
        return usersRepository.verifyLogin(email, password) > 0;
	}


}
