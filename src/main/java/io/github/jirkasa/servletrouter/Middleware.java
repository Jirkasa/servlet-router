package io.github.jirkasa.servletrouter;

import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public abstract class Middleware<Request extends ServletRequest, Response extends ServletResponse> implements Handler<Request, Response> {
	private Map<String, String> pathParams;

	@Override
	public final void setPathParams(Map<String, String> pathParams) {
		this.pathParams = pathParams;
	}
	
	public String getPathParam(String paramName) {
		if (pathParams == null) return null;
		return pathParams.get(paramName);
	}

	@Override
	public boolean matchesFullPath() {
		return false;
	}
}