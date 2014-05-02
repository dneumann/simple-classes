package com.howitest.simpleclasses;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.hamcrest.CoreMatchers.containsString;

import org.junit.Before;
import org.junit.Test;

public class DictionaryRestClientTest {
	
	private Http httpMock;
	private DictionaryRestClient clientSut;

	@Before
	public void beforeEachTest() {
		httpMock = mock(Http.class);
		clientSut = new DictionaryRestClient();
		clientSut.setHttp(httpMock);
	}
	
	@Test
	public void shouldAddWordSuccessfully() {
		when(httpMock.submitPut("mock", "Attrappe")).thenReturn("OK");
		String returnedMessage = clientSut.addWord("mock", "Attrappe");
		
		assertEquals("Word added: mock. Translation: Attrappe.", returnedMessage);
	}

	@Test
	public void shouldFailWithAnErrorMessage() {
		when(httpMock.submitPut(anyString(), anyString())).thenReturn("No connection to server.");
		String returnedMessage = clientSut.addWord("mock", "Attrappe");
		
		assertThat(returnedMessage, containsString("Reason: No connection to server."));
	}
	
	@Test
	public void shouldCallTheRightMethod() {
		clientSut.addWord("mock", "Attrappe");
		
		verify(httpMock).submitPut("mock", "Attrappe");
	}

}
