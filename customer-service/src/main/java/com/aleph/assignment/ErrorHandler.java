package com.aleph.assignment;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.aleph.assignment.model.response.ExceptionResponse;

@ControllerAdvice
@RestController
public class ErrorHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(RuntimeException.class)
    protected ResponseEntity<Object> handleAllException(RuntimeException ex, WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(false, ex.getMessage());
        return new ResponseEntity<Object>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
