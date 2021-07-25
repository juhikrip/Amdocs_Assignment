package com.uxpsystems.assignement.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.uxpsystems.assignement.util.Constant;

@ControllerAdvice
public class ExceptionHandler {
	 @ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = Constant.STATUS_NOT_CORRECT)
	 @org.springframework.web.bind.annotation.ExceptionHandler(HttpMessageNotReadableException.class)
	    public void handleException() {
	        //Handle Exception Here...
	    }
	 @ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = Constant.USER_NOT_FOUND)
	 @org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
	 public void Exception() {
	        //Handle Exception Here...
	    }
	
}
