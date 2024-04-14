package io.github.jirkasa.servletrouter;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * Represents component that is used to handle incoming requests for specific path (usually) in application.
 * @param <Request> Type of ServletRequest.
 * @param <Response> Type of ServletResponse.
 */
public abstract class Controller<Request extends ServletRequest, Response extends ServletResponse> {
	/** Path parameters set by {@link #setPathParams(Map) setPathParams} method. */
	private Map<String, String> pathParams = null;
	
	/**
	 * Sets path parameters.
	 * @param pathParams Map of path parameters.
	 */
	final void setPathParams(Map<String, String> pathParams) {
		this.pathParams = pathParams;
	}
	
	/**
	 *  Returns path parameter.
	 * @param paramName Name of path parameter.
	 * @return Value of path parameter or null.
	 */
	public String getPathParam(String paramName) {
		if (this.pathParams == null) return null;
		return pathParams.get(paramName);
	}
	
	/**
	 * Handles request.
	 * @param request Request to be handled.
	 * @param response Response to be handled.
	 * @return Determines whether handlers chain should continue or not.
	 * @throws Exception
	 */
	public abstract boolean handle(Request request, Response response) throws Exception;
	
	/**
	 * Forwards request to JSP page. In some rare situations it is also possible to forward request to another servlet (basically even the same but with different route).
	 * @param path Path to JSP page (or servlet).
	 * @param request Request object to be passed to JSP page.
	 * @param response Response object to be passed to JSP page.
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void forwardTo(String path, Request request, Response response) throws ServletException, IOException {
		request.getRequestDispatcher(path).forward(request, response);
	}
}