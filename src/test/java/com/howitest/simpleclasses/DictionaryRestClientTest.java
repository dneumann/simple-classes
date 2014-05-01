package com.howitest.simpleclasses;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Test;

public class DictionaryRestClientTest {

	@Test
	public void shouldAddWordSuccessfully() {
		Http httpMock = mock(Http.class);
		when(httpMock.submitPut("mock", "Attrappe")).thenReturn("OK");
		DictionaryRestClient client = new DictionaryRestClient();
		client.setHttp(httpMock);
		
		String returnedMessage = client.addWord("mock", "Attrappe");
		assertEquals("Word added: mock. Translation: Attrappe.", returnedMessage);
	}

}
