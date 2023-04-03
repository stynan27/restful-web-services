package com.seamus.rest.webservices.restfulwebservices.user;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

@Entity(name = "user_details") // JPA to manage this Bean - rename to user_details
public class User {

	protected User() {
		
	}
	
	@Id
	@GeneratedValue // generate this in the background
	private Integer id;
	
	@Size(min=2, message = "Name should have at least 2 characters")
	@JsonProperty("user_name")
	private String name;
	
	@Past(message = "Birthdate should be in the past")
	@JsonProperty("birth_date")
	@Column(name = "BIRTH_DATE") // make sure to also specify as birth_date in SQL Table
	private LocalDate birthdate;
	
	// Define a one entity to many entities relationship
	// User has a list of posts
	// mappedBy specifies field in Post entity to represent this user.
	@OneToMany(mappedBy = "user")
	@JsonIgnore // don't provide posts directly on get request for users
	private List<Post> posts;
	
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
	
	public List<Post> getPosts() {
		return posts;
	}

	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}

	@Override
	public String toString() {
		return "Users [id=" + id + ", name=" + name + ", birthdate=" + birthdate + "]";
	}
}
