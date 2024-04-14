package io.github.jirkasa.servletrouter;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * Component used internally by {@link io.github.jirkasa.servletrouter.Router Router} to assign handlers to specific paths. It stores path and associated handler.
 * @param <Request> Type of ServletRequest.
 * @param <Response> Type of ServletResponse.
 */
class PathHandler<Request extends ServletRequest, Response extends ServletResponse> {
	/** Path in a form of array (for example ["info", "about", "authors"]). */
	private String[] path;
	/** Handler associated with path. */
	public final Handler<Request, Response> handler;
	
	/**
	 * Creates new path handler.
	 * @param path Path.
	 * @param handler Handler associated to path.
	 */
	public PathHandler(String path, Handler<Request, Response> handler) {
		if (path != null) {
			if (path.startsWith("/")) {
				path = path.substring(1, path.length());
			}
			if (path.endsWith("/")) {
				path = path.substring(0, path.length()-1);
			}
			this.path = path.split("/", -1);
		} else {
			this.path = null;
		}
		this.handler = handler;
	}
	
	/**
	 * Checks whether request path matches path for handler.
	 * @param requestPath Request path.
	 * @return True if request path matches path for handler. Otherwise false.
	 */
	public boolean matches(String[] requestPath) {
		// if handler should be used for every path
		if (path == null) return true;
		
		if (handler.matchesFullPath() && path.length != requestPath.length) return false;
		if (path.length > requestPath.length) return false;
		
		for (int i = 0; i < path.length; i++) {
			if (path[i].startsWith(":")) continue;
			if (!path[i].equals(requestPath[i])) return false;
		}
		
		return true;
	}
	
	/**
	 * Returns offset that should be added to path offset when router calls next router.
	 * <p>
	 * For example when request path is /info/about/authors and handler path is /info, it returns 1.
	 * @param requestPath Request path.
	 * @return Offset to be added to path offset when next router is called by router.
	 */
	public int getPathOffsetForNextRouter(String[] requestPath) {
		if (path.length > requestPath.length) return -1; 
		
		int offset = 0;
		
		if (path == null) return offset;
		
		for (; offset < path.length; offset++) {
			if (path[offset].startsWith(":")) continue;
			if (!path[offset].equals(requestPath[offset])) break;
		}
		
		return offset;
	}
	
	/**
	 * Creates and returns map of path parameters based on passed request path and handler path.
	 * @param requestPath Request path.
	 * @return Map of path parameters.
	 */
	public Map<String, String> createPathParams(String[] requestPath) {
		Map<String, String> pathParams = new HashMap<String, String>();
		
		if (path == null) return pathParams;
		
		for (int i = 0; i < path.length && i < requestPath.length; i++) {
			if (path[i].startsWith(":")) {
				pathParams.put(path[i].substring(1), requestPath[i]);
			}
		}
		
		return pathParams;
	}
}