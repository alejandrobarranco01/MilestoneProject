package com.gcu.data.entity;

import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("POSTS")
public class PostEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column("POST_ID")
	private Long postId;

	@Column("DATE")
	private Date date;

	@Column("TITLE")
	private String title;

	@Column("TEXT")
	private String text;

	@Column("AUTHOR_ID")
	private Long authorId;

	@Column("AUTHOR_USERNAME")
	private String authorUsername;

	public PostEntity(Date date, String title, String text, Long authorId, String authorUsername) {
		this.date = date;
		this.title = title;
		this.text = text;
		this.authorId = authorId;
		this.authorUsername = authorUsername;
	}

	public Long getPostId() {
		return postId;
	}

	public void setPostId(Long postId) {
		this.postId = postId;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Long getAuthorId() {
		return authorId;
	}

	public void setAuthorId(Long authorId) {
		this.authorId = authorId;
	}

	public String getAuthorUsername() {
		return authorUsername;
	}

	public void setAuthorUsername(String authorUsername) {
		this.authorUsername = authorUsername;
	}

}
