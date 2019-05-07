package com.codingchallenge.drugtransactionApi.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.codingchallenge.drugtransactionApi.model.Transaction;
import com.codingchallenge.drugtransactionApi.service.DrugTransactionService;

@RestController
@RequestMapping("/transactions")
public class DrugTransactionController {

	@Autowired
	private DrugTransactionService dtservice;

	@RequestMapping(value = "/transaction", method = RequestMethod.POST)
	public ResponseEntity<?> saveTransaction(@Valid @RequestBody Transaction transaction) {
		int size = dtservice.saveTransaction(transaction);
		return new ResponseEntity<>(size,HttpStatus.CREATED);
	}
}
