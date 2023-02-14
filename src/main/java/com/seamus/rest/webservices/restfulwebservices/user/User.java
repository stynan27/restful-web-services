package com.seamus.rest.webservices.restfulwebservices.user;

import java.time.LocalDate;

public class User {

	private Integer id;
	private String username;
	private LocalDate birthdate;
	
	public User(Integer id, String username, LocalDate birthdate) {
		super();
		this.id = id;
		this.username = username;
		this.birthdate = birthdate;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public LocalDate getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(LocalDate birthdate) {
		this.birthdate = birthdate;
	}

	@Override
	public String toString() {
		return "Users [id=" + id + ", username=" + username + ", birthdate=" + birthdate + "]";
	}
}
