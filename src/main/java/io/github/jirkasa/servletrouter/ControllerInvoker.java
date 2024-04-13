package io.github.jirkasa.servletrouter;

import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

class ControllerInvoker<Request extends ServletRequest, Response extends ServletResponse> implements Handler<Request, Response> {
	private Class<? extends Controller<Request, Response>> controllerClass;
	private Map<String, String> pathParams;
	
	public ControllerInvoker(Class<? extends Controller<Request, Response>> controller) {
		this.controllerClass = controller;
	}
	
	@Override
	public boolean handle(Request request, Response response) throws Exception {
		Controller<Request, Response> controller = controllerClass.getConstructor(ServletContext.class).newInstance(request.getServletContext());
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