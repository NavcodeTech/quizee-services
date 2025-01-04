package com.example.quizeeApp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.example.quizeeApp.entity.ErrorResponse;

@ControllerAdvice
public class CommonExceptionHandler {
	@ExceptionHandler(MyBusinessException.class)
	public ResponseEntity<Object> handleBadRequestException(MyBusinessException e, WebRequest wr) {
		ErrorResponse er = new ErrorResponse(HttpStatus.BAD_REQUEST, e.getMessage());
		return new ResponseEntity<>(er, HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> handleGeneralxception(RuntimeException e, WebRequest wr) {
		ErrorResponse er = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
		return new ResponseEntity<>(er, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
