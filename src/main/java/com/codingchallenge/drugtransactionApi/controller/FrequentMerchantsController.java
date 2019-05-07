package com.codingchallenge.drugtransactionApi.controller;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.codingchallenge.drugtransactionApi.Exceptions.InsufficientTransactionsException;
import com.codingchallenge.drugtransactionApi.service.DrugTransactionService;

@RestController
public class FrequentMerchantsController {
	final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private DrugTransactionService utService;

	@RequestMapping(value = "/freqMerchants/{userId}", method = RequestMethod.GET)
	public ResponseEntity<ArrayList<String>> fetchFrequentMerchants(@PathVariable("userId") long userid) {

		ArrayList<String> al = null;
		try {
			al = utService.fetchFrequentlyVisited(userid);
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

		ResponseEntity<ArrayList<String>> responseEntity = new ResponseEntity<ArrayList<String>>(al, HttpStatus.OK);
		return responseEntity;
	}

}
