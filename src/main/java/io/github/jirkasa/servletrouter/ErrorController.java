package io.github.jirkasa.servletrouter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * Represents {@link io.github.jirkasa.servletrouter.Controller Controller} that can be set to {@link io.github.jirkasa.servletrouter.Router Router} using {@link io.github.jirkasa.servletrouter.Router#registerErrorController(Class) Router} method. It then catches any exceptions in the router's handle method.
 * @param <Request> Type of ServletRequest.
 * @param <Response> Type of ServletResponse.
 */
public abstract class ErrorController<Request extends ServletRequest, Response extends ServletResponse> extends Controller<Request, Response> {
	/** Catched exception. */
	private Exception exception;
	
	/**
	 * Creates new error controller.
	 * @param exception Exception that the controller should process.
	 */
	public ErrorController(Exception exception) {
		this.exception = exception;
	}
	
	/**
	 * Returns catched exception.
	 * @return Catched exception.
	 */
	public Exception getException() {
		return exception;
	}
}