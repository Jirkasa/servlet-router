package io.github.jirkasa.servletrouter;

import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * Component used internally by {@link io.github.jirkasa.servletrouter.Router Router} to create handlers for creating and calling {@link io.github.jirkasa.servletrouter.Controller controllers}.
 * @param <Request> Type of ServletRequest.
 * @param <Response> Type of ServletResponse.
 */
class ControllerInvoker<Request extends ServletRequest, Response extends ServletResponse> implements Handler<Request, Response> {
	/** Controller class which instance is created and called when {@link #handle(ServletRequest, ServletResponse) handle} method is called. */
	private Class<? extends Controller<Request, Response>> controllerClass;
	/** Path parameters set by {@link #setPathParams(Map) setPathParams} method. */
	private Map<String, String> pathParams;
	
	/**
	 * Creates new controller invoker.
	 * @param controller Controller class that should be used.
	 */
	public ControllerInvoker(Class<? extends Controller<Request, Response>> controller) {
		this.controllerClass = controller;
	}
	
	@Override
	public boolean handle(Request request, Response response) throws Exception {
		Controller<Request, Response> controller = controllerClass.getConstructor().newInstance();
		controller.setPathParams(pathParams);
		return controller.handle(request, response);
	}

	@Override
	public void setPathParams(Map<String, String> pathParams) {
		this.pathParams = pathParams;
	}

	@Override
	public boolean matchesFullPath() {
		return true;
	}
}