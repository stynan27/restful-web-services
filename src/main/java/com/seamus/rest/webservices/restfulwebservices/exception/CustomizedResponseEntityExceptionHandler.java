package com.seamus.rest.webservices.restfulwebservices.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.seamus.rest.webservices.restfulwebservices.user.UserNotFoundException;

@ControllerAdvice // make available to ALL controllers
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
	
	// All exceptions go here!
	@ExceptionHandler(Exception.class) // rename method!!!
	public final ResponseEntity<ErrorDetails> handleAllExceptions(Exception ex, WebRequest request) throws Exception {
		ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), 
				ex.getMessage(), request.getDescription(false));
		
		return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	// 404 Exceptions come here!
	@ExceptionHandler(UserNotFoundException.class)
	public final ResponseEntity<ErrorDetails> handleUserNotFoundException(Exception ex, WebRequest request) throws Exception {
		ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), 
				ex.getMessage(), request.getDescription(false));
		
		return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.NOT_FOUND);
	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		
		//ex.getFieldError().getDefaultMessage() -> returns the FIRST error message (from message in jakarta validation on object)
		//ex.getFieldErrors() -> list of ALL validation errors
		//ex.getFieldErrorCount() -> get COUNT of all validation errors
		
		ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), 
				"Total errors: " + ex.getErrorCount() + ", First Error: " + ex.getFieldError().getDefaultMessage(), request.getDescription(false));
		
		return new ResponseEntity(errorDetails, HttpStatus.BAD_REQUEST);
	}
}
