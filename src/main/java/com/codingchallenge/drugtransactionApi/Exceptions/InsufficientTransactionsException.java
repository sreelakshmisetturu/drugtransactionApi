package com.codingchallenge.drugtransactionApi.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class InsufficientTransactionsException extends RuntimeException{

   private static final long serivalVersionUID =1L;
   public InsufficientTransactionsException(String message){
		super(message);
	}
}
