package com.howitest.simpleclasses;

import java.io.IOException;


public class DictionaryRestClient {
	
	private Http http = new Http();
	
	// for unit testing
	void setHttp(Http newHttp) {
		http = newHttp;
	}
		
	public String addWord(String english, String german) throws IOException {
		String answerMessage = http.submitPut(english, german);
		if ("OK".equals(answerMessage)) {
			return "Word added: " + english + ". Translation: " + german + ".";
		} else {
			return "Word could not be added: " + english + ". Reason: " + answerMessage + ".";
		}
	}
}
