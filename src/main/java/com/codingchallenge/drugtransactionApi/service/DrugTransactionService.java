package com.codingchallenge.drugtransactionApi.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.codingchallenge.drugtransactionApi.DrugtransactionApiApplication;
import com.codingchallenge.drugtransactionApi.model.Transaction;

/**
 * @author sreelakshmi 
 *         Spring managed class which has methods and data structures to store
 *         transactions, user-merchant data and query top three visited
 *         merchants for a given user. usermerchantmap - mapping between userid,
 *         merchant and number of times that user visited the merchant.
 *         transactionHistory - data structure to store all transactions. This
 *         data can be used for future enhancements of api. userfrequency -
 *         stores data of number of transactions made by a user.
 */
@Component
@Scope("singleton")
public class DrugTransactionService {

	final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private HashMap<Long, HashMap<String, Integer>> usermerchantmap;
	private HashMap<Long, Transaction> transactionHistory;
	private HashMap<Long, Integer> userfrequency;

	@PostConstruct
	public void init() {
		logger.info("initilizing data store");
		if (usermerchantmap == null) {
			usermerchantmap = new HashMap<Long, HashMap<String, Integer>>();
			transactionHistory = new HashMap<Long, Transaction>();
			userfrequency = new HashMap<Long, Integer>();
		}
	}

	public void saveTransaction(Transaction transaction) throws Exception{

		long userid = transaction.getUserId();
		String merchant = transaction.getMerchant();

		// storing all transactions help in future extension of api
		transactionHistory.put(transaction.getTranId(), transaction);
		int freq = userfrequency.getOrDefault(userid, 0);
		
		// if user made 5 transactions, no need to increment the frequency.This should change if delete transaction is implemented in future.
		if(freq < 5){
			userfrequency.put(userid, freq + 1);
		}
		addMerchantFrequency(usermerchantmap, userid, merchant);
		logger.info("transaction saved: txn-id - "+transaction.getTranId());
	}

	private void addMerchantFrequency(HashMap<Long, HashMap<String, Integer>> usermerchantmap, long userid,
			String merchant) {
		HashMap<String, Integer> merchantFreq = usermerchantmap.getOrDefault(userid, new HashMap<String, Integer>());
		int freq = merchantFreq.getOrDefault(merchant, 0);
		merchantFreq.put(merchant, freq + 1);
		usermerchantmap.put(userid, merchantFreq);
	}

	public ArrayList<String> fetchFrequentlyVisited(long userid) throws Exception{
		
		logger.info("querying for userid "+userid);
		
		ArrayList<String> topthree = null;
		if (userfrequency.getOrDefault(userid, 0) >= 5) {
			topthree = new ArrayList<>();
			HashMap<String, Integer> merchantfreq = usermerchantmap.get(userid);
			topthree = findTopThree(merchantfreq);
		}

		return topthree;

	}

	/**
	 * Using bucket sort fashioned algorithm to sort top n (three in here)
	 * visited merchants of a user. First, finding maximum frequency a merchant
	 * can have from the merchantfreq map. Create an array of arraylists, with
	 * index of array as frequency and arraylist of that index consisting of
	 * merchants having that frequency. Iterate over the array of arraylists
	 * from last index to first, store top three merchants and return them.
	 * complexity of the algorithm is O(n), where n is the maximum of[max frequency
	 * present in merchantfreq map or size of merchantfreq map].
	 *
	 */
	private ArrayList<String> findTopThree(HashMap<String, Integer> merchantfreq) {

		int max = 0;
		ArrayList<String> result = new ArrayList<String>();

		for (Map.Entry<String, Integer> entry : merchantfreq.entrySet()) {
			max = Math.max(max, entry.getValue());
		}
		
		ArrayList<String>[] frequencybucket = (ArrayList<String>[]) new ArrayList[max + 1];
		for (int i = 1; i <= max; i++) {
			frequencybucket[i] = new ArrayList<String>();
		}

		for (Map.Entry<String, Integer> entry : merchantfreq.entrySet()) {
			int count = entry.getValue();
			String merchant = entry.getKey();
			frequencybucket[count].add(merchant);
		}

		for (int j = max; j >= 1; j--) {
			if (frequencybucket[j].size() > 0) {
				for (String merch : frequencybucket[j]) {
					result.add(merch);
					if (result.size() == 3) {
						return result;
					}
				}
			}
		}

		return result;
	}

	
	// for unit testing purpose
	public HashMap<Long, HashMap<String, Integer>> getUsermerchantmap() {
		return usermerchantmap;
	}

	public void setUsermerchantmap(HashMap<Long, HashMap<String, Integer>> usermerchantmap) {
		this.usermerchantmap = usermerchantmap;
	}

	public HashMap<Long, Transaction> getTransactionHistory() {
		return transactionHistory;
	}

	public void setTransactionHistory(HashMap<Long, Transaction> transactionHistory) {
		this.transactionHistory = transactionHistory;
	}

	public HashMap<Long, Integer> getUserfrequency() {
		return userfrequency;
	}

	public void setUserfrequency(HashMap<Long, Integer> userfrequency) {
		this.userfrequency = userfrequency;
	}
}
