package io.github.jirkasa.servletrouter;

import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public abstract class Controller<Request extends ServletRequest, Response extends ServletResponse> {
	private Map<String, String> pathParams;
	
	final void setPathParams(Map<String, String> pathParams) {
		this.pathParams = pathParams;
	}
	
	public String getPathParam(String paramName) {
		if (this.pathParams == null) return null;
		return pathParams.get(paramName);
	}
	
	public abstract boolean handle(Request request, Response response) throws Exception;
	
	// todo - přidat metody pro často používané věci - třeba forwardování do dalšího servletu - forwardTo
}