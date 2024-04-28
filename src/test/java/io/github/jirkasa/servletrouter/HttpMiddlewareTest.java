package io.github.jirkasa.servletrouter;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.net.MalformedURLException;

import org.junit.jupiter.api.Test;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class HttpMiddlewareTest {
	@Test
	void itShouldHandleRequest() throws FailingHttpStatusCodeException, MalformedURLException, IOException {
		try (final WebClient webClient = new WebClient()) {
			final HtmlPage page = webClient.getPage("http://localhost:8080/servlet-router-tests/http-middleware/handle-request");
			assertEquals("Request has been handled by middleware", page.getTitleText());
		}
	}
	
	@Test
	void itShouldSetAttributeToRequest() throws FailingHttpStatusCodeException, MalformedURLException, IOException {
		try (final WebClient webClient = new WebClient()) {
			final HtmlPage page = webClient.getPage("http://localhost:8080/servlet-router-tests/http-middleware/set-test-attribute");
			assertEquals("Test attribute: test_value", page.getTitleText());
		}
	}
	
	@Test
	void handlersChainShouldNotContinueWhenFalseIsReturned() throws FailingHttpStatusCodeException, MalformedURLException, IOException {
		try (final WebClient webClient = new WebClient()) {
			final HtmlPage page = webClient.getPage("http://localhost:8080/servlet-router-tests/http-middleware/should-not-continue");
			assertEquals("It will not continue", page.getTitleText());
		}
	}
	
	@Test
	void itShouldGetPathParameter() throws FailingHttpStatusCodeException, MalformedURLException, IOException {
		try (final WebClient webClient = new WebClient()) {
			final HtmlPage page = webClient.getPage("http://localhost:8080/servlet-router-tests/http-middleware/path-param/myTestPARAMETER");
			assertEquals("Parameter: myTestPARAMETER", page.getTitleText());
		}
	}
}