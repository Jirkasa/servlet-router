package io.github.jirkasa.servletrouter;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.net.MalformedURLException;

import org.junit.jupiter.api.Test;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class BaseURLAttributeSetterTest {
	@Test
	void itShouldSetBaseURLAttribute() throws FailingHttpStatusCodeException, MalformedURLException, IOException {
		try (final WebClient webClient = new WebClient()) {
			final HtmlPage page = webClient.getPage("http://localhost:8080/servlet-router-tests/base-url-attribute-setter/set-attribute");
			assertEquals("BASE URL: http://localhost:8080/servlet-router-tests", page.getTitleText());
		}
	}
	
	@Test
	void itShouldSetBaseURLAttributeWithCustomName() throws FailingHttpStatusCodeException, MalformedURLException, IOException {
		try (final WebClient webClient = new WebClient()) {
			final HtmlPage page = webClient.getPage("http://localhost:8080/servlet-router-tests/base-url-attribute-setter/set-attribute-with-custom-name");
			assertEquals("BASE URL: http://localhost:8080/servlet-router-tests", page.getTitleText());
		}
	}
}