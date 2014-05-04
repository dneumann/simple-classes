package com.howitest.simpleclasses;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.hamcrest.CoreMatchers.containsString;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

public class DictionaryRestClientTest {
	
	private DictionaryRestClient clientSut;
	private Http httpMock;

	@Before
	public void beforeEachTest() {
		clientSut = new DictionaryRestClient();
		httpMock = mock(Http.class);
		clientSut.setHttp(httpMock);
	}
	
	@Test
	public void shouldAddWordSuccessfully() throws IOException {
		when(httpMock.submitPut("mock", "Attrappe")).thenReturn("OK");
		String returnedMessage = clientSut.addWord("mock", "Attrappe");
		
		assertEquals("Word added: mock. Translation: Attrappe.", returnedMessage);
	}

	@Test
	public void shouldFailWithAnErrorMessage() throws IOException {
		when(httpMock.submitPut(anyString(), anyString())).thenReturn("Word already exists.");
		String returnedMessage = clientSut.addWord("mock", "Attrappe");
		
		assertThat(returnedMessage, containsString("Reason: Word already exists."));
	}
	
	@Test
	public void shouldCallTheRightMethod() throws IOException {
		clientSut.addWord("mock", "Attrappe");
		
		verify(httpMock).submitPut("mock", "Attrappe");
	}
	
	@Test(expected=IOException.class)
	public void shouldPropagateException() throws IOException {
		when(httpMock.submitPut(anyString(), anyString())).thenThrow(new IOException());
		clientSut.addWord("mock", "Attrappe");
	}

}
