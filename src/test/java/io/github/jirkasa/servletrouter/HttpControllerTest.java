package io.github.jirkasa.servletrouter;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.net.MalformedURLException;

import org.junit.jupiter.api.Test;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class HttpControllerTest {
	@Test
	void itShouldHandleRequest() throws FailingHttpStatusCodeException, MalformedURLException, IOException {
		try (final WebClient webClient = new WebClient()) {
			final HtmlPage page = webClient.getPage("http://localhost:8080/servlet-router-tests/http-controller/handle-request");
			assertEquals("Request has been handled", page.getTitleText());
		}
	}
	
	@Test
	void itShouldHandleGETRequest() throws FailingHttpStatusCodeException, MalformedURLException, IOException {
		try (final WebClient webClient = new WebClient()) {
			final HtmlPage page = webClient.getPage("http://localhost:8080/servlet-router-tests/http-controller/handle-get-request");
			assertEquals("GET Request has been handled", page.getTitleText());
		}
	}
	
	@Test
	void itShouldReturn405WhenMethodIsNotImplementedForController() throws FailingHttpStatusCodeException, MalformedURLException, IOException {
		try (final WebClient webClient = new WebClient()) {
			webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);
			final HtmlPage page = webClient.getPage("http://localhost:8080/servlet-router-tests/http-controller/empty-controller");
			assertEquals(405, page.getWebResponse().getStatusCode());
		}
	}
	
	@Test
	void itShouldContinueHandlersChainWhenMethodIsNotImplementedAndSkipUnimplementedMethodsIsSetToTrue() throws FailingHttpStatusCodeException, MalformedURLException, IOException {
		try (final WebClient webClient = new WebClient()) {
			final HtmlPage page = webClient.getPage("http://localhost:8080/servlet-router-tests/http-controller/skip-unimplemented-methods/empty-controller");
			assertEquals("Skip unimplemented methods - 404", page.getTitleText());
		}
	}
	
	@Test
	void itShouldGetPathParameter() throws FailingHttpStatusCodeException, MalformedURLException, IOException {
		try (final WebClient webClient = new WebClient()) {
			final HtmlPage page = webClient.getPage("http://localhost:8080/servlet-router-tests/http-controller/path-param/myParameter");
			assertEquals("Parameter: myParameter", page.getTitleText());
		}
	}
	
	@Test
	void itShouldGetPathParameterInNestedRouter() throws FailingHttpStatusCodeException, MalformedURLException, IOException {
		try (final WebClient webClient = new WebClient()) {
			final HtmlPage page = webClient.getPage("http://localhost:8080/servlet-router-tests/http-controller/path-param-router/myValue/controller");
			assertEquals("Parameter: myValue", page.getTitleText());
		}
	}
	
	@Test
	void itShouldForwardToJSPPage() throws FailingHttpStatusCodeException, MalformedURLException, IOException {
		try (final WebClient webClient = new WebClient()) {
			final HtmlPage page = webClient.getPage("http://localhost:8080/servlet-router-tests/http-controller/forward-to-jsp");
			assertEquals("JSP Page", page.getTitleText());
		}
	}
	
	@Test
	void itShouldForwardToServlet() throws FailingHttpStatusCodeException, MalformedURLException, IOException {
		try (final WebClient webClient = new WebClient()) {
			final HtmlPage page = webClient.getPage("http://localhost:8080/servlet-router-tests/http-controller/forward-to-servlet");
			assertEquals("Request has been handled", page.getTitleText());
		}
	}
	
	@Test
	void itShouldContinueHandlersChain() throws FailingHttpStatusCodeException, MalformedURLException, IOException {
		try (final WebClient webClient = new WebClient()) {
			final HtmlPage page = webClient.getPage("http://localhost:8080/servlet-router-tests/http-controller/continue-handlers-chain/first-controller");
			assertEquals("Second controller", page.getTitleText());
		}
	}
}