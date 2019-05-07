package com.codingchallenge.drugtransactionApi.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import com.codingchallenge.drugtransactionApi.model.Transaction;

@Component
@Scope("singleton")
public class DrugTransactionService {

	private HashMap<Long, HashMap<String, Integer>> usermerchantmap;
	private HashMap<Long, Transaction> transactionHistory;
	private HashMap<Long, Long> userfrequency;

	@PostConstruct
	public void init() {
		if (usermerchantmap == null) {
			usermerchantmap = new HashMap<>();
			transactionHistory = new HashMap<>();
			userfrequency = new HashMap<>();
		}
	}

	public int saveTransaction(Transaction transaction) {

		long userid = transaction.getUserId();
		String merchant = transaction.getMerchant();

		// storing all transactions help in future extension of api
		transactionHistory.put(transaction.getTranId(), transaction);
		userfrequency.put(userid, userfrequency.getOrDefault(userid, 0L) + 1L);
		addMerchantFrequency(usermerchantmap, userid, merchant);

		return usermerchantmap.size();
	}

	private void addMerchantFrequency(HashMap<Long, HashMap<String, Integer>> usermerchantmap, long userid,
			String merchant) {
		// TODO Auto-generated method stub

		HashMap<String, Integer> merchantFreq = usermerchantmap.getOrDefault(userid, new HashMap<String, Integer>());
		int freq = merchantFreq.getOrDefault(merchant, 0);
		merchantFreq.put(merchant, freq + 1);
		usermerchantmap.put(userid, merchantFreq);

	}

	public ArrayList<String> fetchFrequentlyVisited(long userid) {

		ArrayList<String> topthree = null;
		if (userfrequency.getOrDefault(userid, 0L) >= 5) {
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
	 * complexity of the algorithm is O(n), where n is the maximum frequency
	 * present in merchantfreq map.
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
		
		for(int j=max; j>=1; j--){
	        if(frequencybucket[j].size()>0){
	            for(String merch: frequencybucket[j]){
	                result.add(merch);
	                if(result.size()==3){
	                    return result;
	                }
	            }
	        }
	    }
	 
	    return result;
	}

}
