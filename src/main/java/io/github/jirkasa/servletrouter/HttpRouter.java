package io.github.jirkasa.servletrouter;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HttpRouter extends Router<HttpServletRequest, HttpServletResponse> {
	public HttpRouter(ServletContext servletContext) {
		super(servletContext);
	}

	@Override
	protected String getPath(HttpServletRequest request) {
		String path = request.getRequestURI().substring(request.getContextPath().length());
		
		if (path.startsWith("/")) {
			path = path.substring(1, path.length());
		}
		if (path.endsWith("/")) {
			path = path.substring(0, path.length()-1);
		}
		
		return path;
	}
}