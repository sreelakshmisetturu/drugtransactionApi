package com.codingchallenge.drugtransactionApi.Exceptions;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class InvalidRequestFormat extends ResponseEntityExceptionHandler {
	@Override

	protected ResponseEntity<Object> handleHttpMessageNotReadable(

			HttpMessageNotReadableException ex, HttpHeaders headers,

			HttpStatus status, WebRequest request) {

		ErrorDetail errorDetail = new ErrorDetail();
		errorDetail.setTimeStamp(new Date().getTime());
		errorDetail.setStatus(status.value());
		errorDetail.setTitle("Message Not Readable");
		errorDetail.setDetail(ex.getMessage());
		errorDetail.setErrormessage(ex.getClass().getName());
		return handleExceptionInternal(ex, errorDetail, headers, status, request);

	}
}
