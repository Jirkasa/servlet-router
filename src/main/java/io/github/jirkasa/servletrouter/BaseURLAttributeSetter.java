package io.github.jirkasa.servletrouter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Middleware that sets base URL attribute to request. By default it is named "BASE_URL", but that can be changed by passing name to constructor.
 */
public class BaseURLAttributeSetter extends HttpMiddleware {
	/** Default name to be used for base URL attribute. */
	private static final String BASE_URL_DEFAULT_ATTRIBUTE_NAME = "BASE_URL";
	
	/** Name of base URL attribute. */
	private String baseURLAttributeName;
	
	/**
	 * Creates new middleware that sets "BASE_URL" attribute to request.
	 */
	public BaseURLAttributeSetter() {
		baseURLAttributeName = BASE_URL_DEFAULT_ATTRIBUTE_NAME;
	}
	
	/**
	 * Creates new middleware that sets base URL attribute to request.
	 * @param baseURLAttributeName Name of attribute.
	 */
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