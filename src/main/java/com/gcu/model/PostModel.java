package com.gcu.model;

import java.util.concurrent.ThreadLocalRandom;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class PostModel {
	@Id
	private Long id;
	private String title;
	private String text;
	private String authorEmail;
	private String date;

	public PostModel() {
		this.id = ThreadLocalRandom.current().nextLong(1, Long.MAX_VALUE);
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setAuthorEmail(String authorEmail) {
		this.authorEmail = authorEmail;
	}

	public String getAuthorEmail() {
		return authorEmail;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getDate() {
		return date;
	}

}
