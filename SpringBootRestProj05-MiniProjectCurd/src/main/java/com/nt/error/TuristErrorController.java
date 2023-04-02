package com.nt.error;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.nt.exception.TouristNotFound;

@RestControllerAdvice
public class TuristErrorController {
	@ExceptionHandler(TouristNotFound.class)
	public ResponseEntity<ErrorDetails> handleTouristNotFound(TouristNotFound tnf){
		ErrorDetails error=new ErrorDetails(LocalDateTime.now(),tnf.getMessage(),"404- Tourist not found");
		return new ResponseEntity<ErrorDetails>(error,HttpStatus.NOT_FOUND);
	}
	
	public ResponseEntity<ErrorDetails> handleAllException(Exception e){
		ErrorDetails error=new ErrorDetails(LocalDateTime.now(),e.getMessage(),"Problem in execution");
		return new ResponseEntity<ErrorDetails>(error,HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
