package com.mindtree.college.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class MyException {

	@ExceptionHandler(StudentException.class)
	public ResponseEntity<?> NoSuchMarks(StudentException ex, WebRequest request) {
		return new ResponseEntity(ex.getMessage(), HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler(CollegeException.class)
	public ResponseEntity<?> NoSuchMarks(CollegeException ex, WebRequest request) {
		return new ResponseEntity(ex.getMessage(), HttpStatus.BAD_REQUEST);
	}
}
