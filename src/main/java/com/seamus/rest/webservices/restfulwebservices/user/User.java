package com.seamus.rest.webservices.restfulwebservices.user;

import java.time.LocalDate;

import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

public class User {

	private Integer id;
	
	@Size(min=2, message = "Name should have at least 2 characters")
	private String name;
	
	@Past(message = "Birthdate should be in the past")
	private LocalDate birthdate;
	
	public User(Integer id, String name, LocalDate birthdate) {
		super();
		this.id = id;
		this.name = name;
		this.birthdate = birthdate;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(LocalDate birthdate) {
		this.birthdate = birthdate;
	}

	@Override
	public String toString() {
		return "Users [id=" + id + ", name=" + name + ", birthdate=" + birthdate + "]";
	}
}
