package com.seamus.rest.webservices.restfulwebservices.user;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import java.net.URI;
import java.util.List;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import jakarta.validation.Valid;

@RestController
public class UserResource {

	private UserDaoService service;
	
	public UserResource(UserDaoService service) {
		this.service = service;
	}
	
	//GET /users
	@GetMapping("/users")
	public List<User> retrieveAllUsers(){
		return service.findAll();
	}
	
	//GET /users/1
	@GetMapping("/users/{id}")
	public EntityModel<User> retrieveUser(@PathVariable int id){
		User user = service.findOne(id);
		
		// catch null (no user) return and return a 404 Not Found Exception
		if (user==null) 
			throw new UserNotFoundException("id:"+id);
		
		// wrap user in entity model to hyperlink resources
		EntityModel<User> entityModel = EntityModel.of(user);
		
		// Create a link pointing to the controller method retrieveAllUsers()
		WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).retrieveAllUsers());
		entityModel.add(link.withRel("all-users")); // with key name "all-users"
		
		return entityModel;
	}
	
	@DeleteMapping("/users/{id}")
	public void deleteUser(@PathVariable int id){
		service.deleteById(id); // returns 200 on successful response
	}
	
	//POST /users
	@PostMapping("/users")
	public ResponseEntity<User> createUser(@Valid @RequestBody User user) { // @RequestBody to map body of a request to a User Bean
		User savedUser = service.save(user);
		
		// location of newly created resource (i.e. /users/4 => /users/{id})
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
						.path("/{id}")
						.buildAndExpand(savedUser.getId())
						.toUri();
		
		// Returns 201 when created with URI location of created user (in response headers)
		return ResponseEntity.created(location).build();
	}
	
}
