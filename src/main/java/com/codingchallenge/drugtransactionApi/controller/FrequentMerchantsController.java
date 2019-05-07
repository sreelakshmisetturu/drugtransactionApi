package com.codingchallenge.drugtransactionApi.controller;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.codingchallenge.drugtransactionApi.Exceptions.InsufficientTransactionsException;
import com.codingchallenge.drugtransactionApi.service.DrugTransactionService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@Api(value="Fetch Frequency api", description="Operations pertaining to querying drug transaction")
public class FrequentMerchantsController {
	final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private DrugTransactionService dtService;

	@RequestMapping(value = "transaction/freqMerchants/{userId}", method = RequestMethod.GET)
	@ApiOperation(value = "Fetch frequently visited  merchants", response = ResponseEntity.class)
	public ResponseEntity<ArrayList<String>> fetchFrequentMerchants(@ApiParam(value = "user id to query Transactions", required = true) @PathVariable("userId") long userid) {

		ArrayList<String> al = null;
		try {
			al = dtService.fetchFrequentlyVisited(userid);
			if (al == null || al.size() == 0) {
				logger.error("Error - Too few transactionsfor userId " + userid);
				throw new InsufficientTransactionsException(
						"Error - Too few transactions.Atleast five transactions are required to fetch three frequently visited merchants for userId "
								+ userid);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error(e.toString());
		}
		HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.APPLICATION_JSON);
		ResponseEntity<ArrayList<String>> responseEntity = new ResponseEntity<ArrayList<String>>(al,headers, HttpStatus.OK);
		return responseEntity;
	}

}
