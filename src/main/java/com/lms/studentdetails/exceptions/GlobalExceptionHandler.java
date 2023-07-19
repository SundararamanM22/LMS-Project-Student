package com.lms.studentdetails.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(AgeUnder18Exception.class)
	public ResponseEntity<Object> ageNotEligibleException(AgeUnder18Exception ex) {
		return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_ACCEPTABLE);
	}
	
	@ExceptionHandler(UpdateResultTableException.class)
	public ResponseEntity<Object> updateResultTableException(UpdateResultTableException ex) {
		return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_ACCEPTABLE);
	}
}
