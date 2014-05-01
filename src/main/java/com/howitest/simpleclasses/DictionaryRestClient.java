package com.howitest.simpleclasses;


public class DictionaryRestClient {
	
	private Http http = new Http();
	
	// for unit testing
	void setHttp(Http newHttp) {
		http = newHttp;
	}
		
	public String addWord(String english, String german) {
		String answerMessage = http.submitPut(english, german);
		if ("OK".equals(answerMessage)) {
			return "Word added: " + english + ". Translation: " + german + ".";
		} else {
			return "Word could not be added: " + english + ". Reason: " + answerMessage + ".";
		}
	}
}
