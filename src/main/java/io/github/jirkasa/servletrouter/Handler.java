package io.github.jirkasa.servletrouter;

import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public interface Handler<Request extends ServletRequest, Response extends ServletResponse> {
	public boolean handle(Request request, Response response) throws Exception;
	public void setPathParams(Map<String, String> pathParams);
	public boolean matchesFullPath();
}