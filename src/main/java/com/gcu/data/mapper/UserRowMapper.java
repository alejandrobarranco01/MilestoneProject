package com.gcu.data.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.Nullable;

import com.gcu.data.entity.UserEntity;

/**
 * The UserRowMapper class implements the RowMapper interface to map database
 * rows to UserEntity objects. It defines the mapping logic for the UserEntity
 * class.
 */
public class UserRowMapper implements RowMapper<UserEntity> {
	/**
	 * Maps a row of the ResultSet to a UserEntity object.
	 *
	 * @param rs     The ResultSet containing the data from the database.
	 * @param rowNum The current row number being processed.
	 * @return A UserEntity object created from the ResultSet data.
	 * @throws SQLException If a SQLException is encountered while processing the
	 *                      ResultSet.
	 */
	@Override
	public UserEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
		return new UserEntity(rs.getString("USERNAME"), rs.getString("EMAIL"), rs.getString("PASSWORD"));
	}

}
