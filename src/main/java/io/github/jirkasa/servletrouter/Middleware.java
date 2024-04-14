package io.github.jirkasa.servletrouter;

import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * Represents component that is used to handle requests. Most of the time it is used performs some kind of operation or check and pass the request on for next processing. For example, it can be used to restrict some routes only to logged in users and so on.
 * @param <Request> Type of ServletRequest.
 * @param <Response> Type of ServletResponse.
 */
public abstract class Middleware<Request extends ServletRequest, Response extends ServletResponse> implements Handler<Request, Response> {
	/** Path parameters set by {@link #setPathParams(Map) setPathParams} method. */
	private Map<String, String> pathParams;

	@Override
	public final void setPathParams(Map<String, String> pathParams) {
		this.pathParams = pathParams;
	}
	
	/**
	 *  Returns path parameter.
	 * @param paramName Name of path parameter.
	 * @return Value of path parameter or null.
	 */
	public String getPathParam(String paramName) {
		if (pathParams == null) return null;
		return pathParams.get(paramName);
	}

	@Override
	public boolean matchesFullPath() {
		return false;
	}
}