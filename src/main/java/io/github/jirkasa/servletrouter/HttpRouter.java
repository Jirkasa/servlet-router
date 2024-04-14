package io.github.jirkasa.servletrouter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Implementation of {@link io.github.jirkasa.servletrouter.Router Router} that uses HttpServletRequest and HttpServletResponse.
 * <p>
 * It can be created directly like this:
 * <pre>
 * HttpRouter router = new HttpRouter();
 * router.register("/", HomeController.class);
 * router.register("/info", infoRouter);
 * </pre>
 * or a subclass can be created (which is recommended) and register methods can be called from the constructor:
 * <pre>
 * public class AppRouter extends HttpRouter {
 *     public AppRouter() {
 *         register("/", HomeController.class);
 *         router.register("/info", infoRouter);
 *     }
 * }
 * </pre>
 */
public class HttpRouter extends Router<HttpServletRequest, HttpServletResponse> {
	@Override
	protected String getRequestPath(HttpServletRequest request) {
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