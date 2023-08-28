package com.virtusahackathon.ebookpreview.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import feign.FeignException;

@ControllerAdvice
public class ExceptionHandlers {

	@ExceptionHandler(FeignException.class)
	public ResponseEntity<Map<String, String>> handleWrongException(FeignException exp) {
		Map<String, String> response=new HashMap<>();
		response.put("error", exp.getMessage());
		return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(response);
	}
}
