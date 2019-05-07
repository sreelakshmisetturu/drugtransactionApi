package com.codingchallenge.drugtransactionApi.service;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import com.codingchallenge.drugtransactionApi.model.Transaction;

@Component
@Scope("singleton")
public class DrugTransactionService {

	private HashMap<Long, HashMap<String, Long>> usertransactions;
	private HashMap<Long, Transaction> transactionHistory;
	private HashMap<Long, Long> userfreq;

	public int saveTransaction(Transaction transaction) {

		if (usertransactions == null) {
			usertransactions = new HashMap<>();
			transactionHistory = new HashMap<>();
			userfreq = new HashMap<>();
		}

		long userid = transaction.getUserId();
		String merchant = transaction.getMerchant();

		// storing all transactions help in future extension of api
		transactionHistory.put(transaction.getTranId(), transaction);

		HashMap<String, Long> merchantFreq = usertransactions.getOrDefault(userid, new HashMap<String, Long>());
		long freq = merchantFreq.getOrDefault(merchant, 0L);
		merchantFreq.put(merchant, freq + 1);
		usertransactions.put(userid, merchantFreq);
		userfreq.put(userid, userfreq.getOrDefault(userid, 0L) + 1L);
		System.out.println("transaction saved successfully!");
		return usertransactions.size();
	}

	public ArrayList<String> fetchFrequentlyVisited(long userid) {
		if (usertransactions == null) {
			usertransactions = new HashMap<>();
			transactionHistory = new HashMap<>();
			userfreq = new HashMap<>();
		}

		ArrayList<String> topthree = new ArrayList<>();
		if (userfreq.getOrDefault(userid, 0L) >= 5) {
			// findTopThree(userid,usertransactions,topthree);
			return topthree;
		}

		return null;

	}

	private void findTopThree() {

	}

}
