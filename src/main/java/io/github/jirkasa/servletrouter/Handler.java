package io.github.jirkasa.servletrouter;

import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * Represents component that handles requests. Can be registered in {@link io.github.jirkasa.servletrouter.Router Router} which is also a handler.
 * @param <Request> Type of ServletRequest.
 * @param <Response> Type of ServletResponse.
 */
public interface Handler<Request extends ServletRequest, Response extends ServletResponse> {
	/**
	 * Handles request.
	 * @param request Request to be handled.
	 * @param response Response to be handled.
	 * @return Determines whether handlers chain should continue or not.
	 * @throws Exception
	 */
	public boolean handle(Request request, Response response) throws Exception;
	
	/**
	 * Sets path parameters.
	 * @param pathParams Map of path parameters.
	 */
	public void setPathParams(Map<String, String> pathParams);
	
	/**
	 * Determines whether handler should be called when full request path matches or not.
	 * @return True indicates that full request path should match path of handler. False indicates that just start of request path should match path of handler.
	 */
	public boolean matchesFullPath();
}