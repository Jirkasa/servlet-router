package io.github.jirkasa.servletrouter;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.net.MalformedURLException;

import org.junit.jupiter.api.Test;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class HttpRouterTest {
	@Test
	void itShouldRegisterHandlerAtSpecifiedPath() throws FailingHttpStatusCodeException, MalformedURLException, IOException {
		try (final WebClient webClient = new WebClient()) {
			final HtmlPage page = webClient.getPage("http://localhost:8080/servlet-router-tests/http-router/test-handler");
			assertEquals("Test handler registered", page.getTitleText());
		}
	}
	
	@Test
	void itShouldRegisterControllerAtSpecifiedPath() throws FailingHttpStatusCodeException, MalformedURLException, IOException {
		try (final WebClient webClient = new WebClient()) {
			final HtmlPage page = webClient.getPage("http://localhost:8080/servlet-router-tests/http-router/test-controller");
			assertEquals("Controller was registered", page.getTitleText());
		}
	}
	
	@Test
	void itShouldHandleErrorViaRegisteredErrorController() throws FailingHttpStatusCodeException, MalformedURLException, IOException {
		try (final WebClient webClient = new WebClient()) {
			webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);
			final HtmlPage page = webClient.getPage("http://localhost:8080/servlet-router-tests/http-router/error");
			assertEquals("Exception caught: Text of exception.", page.getTitleText());
		}
	}
	
	@Test
	void itShouldRegisterSubRouter() throws FailingHttpStatusCodeException, MalformedURLException, IOException {
		try (final WebClient webClient = new WebClient()) {
			webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);
			final HtmlPage page = webClient.getPage("http://localhost:8080/servlet-router-tests/http-router/sub-router/");
			assertEquals("Controller in subrouter.", page.getTitleText());
		}
	}
	
	@Test
	void itShouldRegisterIndexControllerInSubrouter() throws FailingHttpStatusCodeException, MalformedURLException, IOException {
		try (final WebClient webClient = new WebClient()) {
			webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);
			final HtmlPage page = webClient.getPage("http://localhost:8080/servlet-router-tests/http-router/sub-router-2/");
			assertEquals("Controller in subrouter 2.", page.getTitleText());
		}
	}
}