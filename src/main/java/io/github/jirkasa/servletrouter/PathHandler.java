package io.github.jirkasa.servletrouter;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

class PathHandler<Request extends ServletRequest, Response extends ServletResponse> {
	private String[] path;
	public final Handler<Request, Response> handler;
	
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