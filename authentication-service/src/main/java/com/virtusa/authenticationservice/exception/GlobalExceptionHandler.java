package com.virtusa.exception;

import java.util.Arrays;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(RoleNotFoundException.class)
	public ResponseEntity<?> RoleNotFoundException(RoleNotFoundException ex,HttpServletRequest req){
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Role Not Found");
	}
}
