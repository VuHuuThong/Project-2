package com.javaweb.controllerAdvance;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.javaweb.customexception.FieldRequiredException;
import com.javaweb.model.ErrorResponse;


@ControllerAdvice
public class ControllerAdvisor extends ResponseEntityExceptionHandler  {

	 @ExceptionHandler(ArithmeticException.class)
	    public ResponseEntity<Object> handleArithmeticException(
	            ArithmeticException ex, WebRequest request) {
		 
		 ErrorResponse err =new ErrorResponse();
		 err.setError(ex.getMessage());
		 List<String> detials =new ArrayList<>();
		 detials.add("Khong chia duopc cho khong");
		 err.setDetial(detials);
		 
		     return new ResponseEntity<>(err, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	 
	 @ExceptionHandler(FieldRequiredException.class)
	    public ResponseEntity<Object> handleFiled(
	    		FieldRequiredException ex, WebRequest request) {
		 
		 ErrorResponse err =new ErrorResponse();
		 err.setError(ex.getMessage());
		 List<String> detials =new ArrayList<>();
		 detials.add("Thieu Name hoac NumberOfbaseMent roi check lai di");
		 err.setDetial(detials);
		 
		     return new ResponseEntity<>(err, HttpStatus.BAD_GATEWAY);
	    }
}
