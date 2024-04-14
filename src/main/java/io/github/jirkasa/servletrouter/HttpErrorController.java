package io.github.jirkasa.servletrouter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * {@link io.github.jirkasa.servletrouter.ErrorController ErrorController} that uses HttpServletRequest and HttpServletResponse.
 */
public abstract class HttpErrorController extends ErrorController<HttpServletRequest, HttpServletResponse> {
	/**
	 * Creates new error controller.
	 * @param exception Exception that the controller should process.
	 */
	public HttpErrorController(Exception exception) {
		super(exception);
	}
}