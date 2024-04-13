package io.github.jirkasa.servletrouter;

import javax.servlet.ServletContext;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public abstract class ErrorController<Request extends ServletRequest, Response extends ServletResponse> extends Controller<Request, Response> {
	private Exception exception;
	
	public ErrorController(ServletContext servletContext, Exception exception) {
		super(servletContext);
		this.exception = exception;
	}
	
	public Exception getException() {
		return exception;
	}
}