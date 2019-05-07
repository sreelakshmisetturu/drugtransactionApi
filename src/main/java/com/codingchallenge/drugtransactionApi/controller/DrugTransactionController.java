package com.codingchallenge.drugtransactionApi.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.codingchallenge.drugtransactionApi.model.Transaction;
import com.codingchallenge.drugtransactionApi.service.DrugTransactionService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/transactions")
@Api(value="Drug transaction api", description="Operations pertaining to storing drug transaction")
public class DrugTransactionController {
	final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private DrugTransactionService dtservice;

	@RequestMapping(value = "/transaction", method = RequestMethod.POST)
	@ApiOperation(value = "Store transactions", response = ResponseEntity.class)
	public ResponseEntity<?> saveTransaction( @ApiParam(value = "Transaction required to store in datastore", required = true) @Valid @RequestBody Transaction transaction) {
		ResponseEntity<?> responseEntity;
		try {
			dtservice.saveTransaction(transaction);
		} catch (Exception e) {
			logger.error(e.toString());
			responseEntity = new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
			return responseEntity;
		}
		
		responseEntity = new ResponseEntity<>(HttpStatus.CREATED);
		return responseEntity;
		
	}
}
