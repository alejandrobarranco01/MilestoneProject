package com.gcu.data.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import org.springframework.jdbc.core.RowMapper;

import com.gcu.data.entity.PostEntity;

/**
 * The PostRowMapper class implements the RowMapper interface to map database
 * rows to PostEntity objects. It defines the mapping logic for the PostEntity
 * class.
 */
public class PostRowMapper implements RowMapper<PostEntity> {
	/**
	 * Maps a row of the ResultSet to a PostEntity object.
	 *
	 * @param rs     The ResultSet containing the data from the database.
	 * @param rowNum The current row number being processed.
	 * @return A PostEntity object created from the ResultSet data.
	 * @throws SQLException If a SQLException is encountered while processing the
	 *                      ResultSet.
	 */
	@Override
	public PostEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
		Date date = rs.getDate("DATE");
		String title = rs.getString("TITLE");
		String text = rs.getString("TEXT");
		Long authorId = rs.getLong("AUTHOR_ID");
		String authorUsername = rs.getString("AUTHOR_USERNAME");
		return new PostEntity(date, title, text, authorId, authorUsername);
	}

}
