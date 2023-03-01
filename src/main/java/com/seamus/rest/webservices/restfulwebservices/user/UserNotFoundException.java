package com.seamus.rest.webservices.restfulwebservices.user;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

// Return not found response
@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class UserNotFoundException extends RuntimeException { // RuntimeExceptions > Exceptions as they can be prevented programmatically (cleaner code)


	public UserNotFoundException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}
}
