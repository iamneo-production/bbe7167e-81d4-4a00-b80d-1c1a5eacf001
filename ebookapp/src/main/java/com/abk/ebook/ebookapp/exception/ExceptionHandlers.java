package com.abk.ebook.ebookapp.exception;

import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class ExceptionHandlers {

	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<Map<String, String>> handleWrongException(NotFoundException exp) {
		Map<String, String> response=new HashMap<>();
		response.put("error", exp.getMessage());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
	}
}

