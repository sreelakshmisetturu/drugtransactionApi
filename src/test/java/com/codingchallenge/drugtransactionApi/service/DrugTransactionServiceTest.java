package com.codingchallenge.drugtransactionApi.service;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.codingchallenge.drugtransactionApi.model.Transaction;


@RunWith(SpringRunner.class)
@SpringBootTest(classes =  DrugTransactionService.class)
public class DrugTransactionServiceTest {
	
	@Autowired
	DrugTransactionService dtservice;

	@Test
	public void testsaveTransaction() throws Exception {
		Transaction tran1 = new Transaction();
		tran1.setUserId(1);
		tran1.setMerchant("test-drugs");
		tran1.setPrice(0.9);
		tran1.setTranId(1);
		dtservice.saveTransaction(tran1);
		assertNotNull(dtservice.getTransactionHistory().get(tran1.getTranId()));
	}

	@Test
	public void testwithFewTransactions() throws Exception {
		assertNull(dtservice.fetchFrequentlyVisited(1));
	}
	
	@Test
	public void testFetchVisited() throws Exception {
		for(int i=6; i<11;i++){
			Transaction tran1 = new Transaction();
			tran1.setUserId(2);
			tran1.setMerchant("test-drugs");
			tran1.setPrice(0.9);
			tran1.setTranId(i);
			dtservice.saveTransaction(tran1);
		}
		
		ArrayList<String> result = dtservice.fetchFrequentlyVisited(2);
		assertEquals(1,result.size());
		assertEquals("test-drugs", result.get(0));
	}

}
