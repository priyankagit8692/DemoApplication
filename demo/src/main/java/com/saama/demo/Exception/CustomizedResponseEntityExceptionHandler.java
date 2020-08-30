package com.saama.demo.Exception;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler{
	
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> handleAllException(Exception ex, WebRequest request) throws Exception{
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));
		return new ResponseEntity(exceptionResponse,HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(CustomerNotFoundException.class)
	public final ResponseEntity<Object> handleCustomerNotFoundException(CustomerNotFoundException ex, WebRequest request) throws Exception{
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));
		return new ResponseEntity(exceptionResponse,HttpStatus.NOT_FOUND);
	}
	

	
	 @Override
	  protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
	      HttpHeaders headers, HttpStatus status, WebRequest request) {
		 
		 List<String> errors = new ArrayList<String>();
		    for (FieldError error : ex.getBindingResult().getFieldErrors()) {
		        errors.add(error.getField() + ": " + error.getDefaultMessage());
		    }
		    
		    ApiError apiError = 
		      new ApiError(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), errors);
		    return handleExceptionInternal(
		      ex, apiError, headers, apiError.getStatus(), request);
		
	  } 
	 
	 
	/* @Override
	 protected ResponseEntity<Object> handleMissingPathVariable(
				MissingPathVariableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		    ApiError apiError = 
			      new ApiError(HttpStatus.METHOD_NOT_ALLOWED, ex.getLocalizedMessage());
			    return handleExceptionInternal(
			      ex, apiError, headers, apiError.getStatus(), request);
	 }*/
	 
}
