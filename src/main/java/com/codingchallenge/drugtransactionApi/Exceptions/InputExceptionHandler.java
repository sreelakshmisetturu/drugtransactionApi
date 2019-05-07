package com.codingchallenge.drugtransactionApi.Exceptions;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class InputExceptionHandler {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> handleValidationError(MethodArgumentNotValidException

	manve, HttpServletRequest request) {

		ErrorDetail errorDetail = new ErrorDetail();
		errorDetail.setTimeStamp(new Date().getTime());

		errorDetail.setStatus(HttpStatus.BAD_REQUEST.value());

		String requestPath = (String) request.getAttribute("javax.servlet.error.request_uri");

		if (requestPath == null) {

			requestPath = request.getRequestURI();

		}

		errorDetail.setTitle("Validation Failed");

		errorDetail.setDetail("Input validation failed");

		errorDetail.setErrormessage(manve.getClass().getName());

		List<FieldError> fieldErrors = manve.getBindingResult().getFieldErrors();

		for (FieldError fe : fieldErrors) {

			List<InputValidationError> validationErrorList = errorDetail.getErrors().

					get(fe.getField());

			if (validationErrorList == null) {

				validationErrorList = new ArrayList<InputValidationError>();

				errorDetail.getErrors().put(fe.getField(),

						validationErrorList);

			}

			InputValidationError validationError = new InputValidationError();

			validationError.setCode(fe.getCode());

			validationError.setMessage(fe.getDefaultMessage());

			validationErrorList.add(validationError);

		}

		return new ResponseEntity<>(errorDetail, null, HttpStatus.BAD_REQUEST);
	}
}
