package io.github.jirkasa.servletrouter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BaseURLAttributeSetter extends HttpMiddleware {
	private static final String BASE_URL_DEFAULT_ATTRIBUTE_NAME = "BASE_URL";
	
	private String baseURLAttributeName;
	
	public BaseURLAttributeSetter() {
		baseURLAttributeName = BASE_URL_DEFAULT_ATTRIBUTE_NAME;
	}
	
	public BaseURLAttributeSetter(String baseURLAttributeName) {
		this.baseURLAttributeName = baseURLAttributeName;
	}
	
	@Override
	public boolean handle(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String scheme = request.getScheme() + "://";
	    String serverName = request.getServerName();
	    String serverPort = (request.getServerPort() == 80) ? "" : ":" + request.getServerPort();
	    String contextPath = request.getContextPath();
	    
	    String baseURL = scheme + serverName + serverPort + contextPath;
	    
	    request.setAttribute(baseURLAttributeName, baseURL);
	    
		return true;
	}
}