package com.example.trade.store.exception;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class TradeStoreControllerAdvisor extends ResponseEntityExceptionHandler {

	@ExceptionHandler(LowerVersionException.class)
	public ResponseEntity<Object> handleLowerVersionException(LowerVersionException ex, WebRequest request) {

		Map<String, Object> body = new LinkedHashMap<>();
		body.put("timestamp", LocalDateTime.now());
		body.put("message", "Lower Version Trade is submitted");

		return new ResponseEntity<>(body, HttpStatus.NOT_ACCEPTABLE);
	}

	@ExceptionHandler(InvalidMaturityDateException.class)
	public ResponseEntity<Object> handleInvalidMaturityDateException(InvalidMaturityDateException ex,
			WebRequest request) {

		Map<String, Object> body = new LinkedHashMap<>();
		body.put("timestamp", LocalDateTime.now());
		body.put("message", "Maturity date can not be earlier than current date");

		return new ResponseEntity<>(body, HttpStatus.NOT_ACCEPTABLE);
	}
}
