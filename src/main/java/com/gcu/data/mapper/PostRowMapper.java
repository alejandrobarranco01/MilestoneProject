package com.gcu.data.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import org.springframework.jdbc.core.RowMapper;

import com.gcu.data.entity.PostEntity;

public class PostRowMapper implements RowMapper<PostEntity> {

	@Override
	public PostEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
		Date date = rs.getTimestamp("DATE");
		String title = rs.getString("TITLE");
		String text = rs.getString("TEXT");
		Long authorId = rs.getLong("AUTHOR_ID");
		String authorUsername = rs.getString("AUTHOR_USERNAME");
		return new PostEntity(date, title, text, authorId, authorUsername);
	}

}
