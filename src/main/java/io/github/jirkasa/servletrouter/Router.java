package io.github.jirkasa.servletrouter;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * Registers handlers and controllers to specific paths and performs routing for incoming requests.
 * @param <Request> Type of ServletRequest.
 * @param <Response> Type of ServletResponse.
 */
public abstract class Router<Request extends ServletRequest, Response extends ServletResponse> implements Handler<Request, Response> {
	/** List of registered handlers. */
	private List<PathHandler<Request, Response>> handlers = new LinkedList<PathHandler<Request, Response>>();
	/** Registered error controller. */
	private Class<? extends ErrorController<Request, Response>> errorControllerClass = null;
	/** Path parameters set by {@link #setPathParams(Map) setPathParams} method. */
	private Map<String, String> pathParams = null;
	
	/**
	 * Registers handler(s) for the specified path.
	 * @param path Path for which the handler(s) is to be called.
	 * @param handlers Handler(s).
	 */
	@SafeVarargs
	public final void register(String path, Handler<Request, Response>... handlers) {
		for (Handler<Request, Response> handler : handlers) {
			PathHandler<Request, Response> pathHandler = new PathHandler<Request, Response>(path, handler);
			this.handlers.add(pathHandler);
		}
	}
	
	/**
	 * Registers controller(s) for the specified path.
	 * @param <T> Controller class.
	 * @param path Path for which the controller(s) is to be called.
	 * @param controllers Controller(s).
	 */
	@SafeVarargs
	public final <T extends Controller<Request, Response>> void register(String path, Class<T>... controllers) {
		registerControllers(path, controllers);
	}
	
	/**
	 * Registers list of handlers for the specified path.
	 * @param path Path for which the handlers are to be called.
	 * @param handlers List of handlers.
	 */
	public final void register(String path, List<Handler<Request, Response>> handlers) {
		Iterator<Handler<Request, Response>> iterator = handlers.iterator();
		
		while (iterator.hasNext()) {
			Handler<Request, Response> handler = iterator.next();
			PathHandler<Request, Response> pathHandler = new PathHandler<Request, Response>(path, handler);
			this.handlers.add(pathHandler);
		}
	}
	
	/**
	 * Registers list of handlers and controller(s) for the specified path.
	 * @param <T> Controller class.
	 * @param path Path for which the handlers and controller(s) are to be called.
	 * @param handlers List of handlers.
	 * @param controllers Controller(s).
	 */
	@SafeVarargs
	public final <T extends Controller<Request, Response>> void register(String path, List<Handler<Request, Response>> handlers, Class<T>... controllers) {
		register(path, handlers);
		register(path, controllers);
	}
	
	/**
	 * Registers list of handlers and list of controllers for the specified path.
	 * @param <T> Controller class.
	 * @param path Path for which the handlers and controllers are to be called.
	 * @param handlers List of handlers.
	 * @param controllers List of controllers.
	 */
	public final <T extends Controller<Request, Response>> void register(String path, List<Handler<Request, Response>> handlers, List<Class<T>> controllers) {
		register(path, handlers);
		registerListOfControllers(path, controllers);
	}
	
	/**
	 *  Registers array of handlers and controller(s) for the specified path.
	 * @param <T> Controller class.
	 * @param path Path for which the handlers and controller(s) are to be called.
	 * @param handlers Array of handlers.
	 * @param controllers Controller(s).
	 */
	@SafeVarargs
	public final <T extends Controller<Request, Response>> void register(String path, Handler<Request, Response>[] handlers, Class<T>... controllers) {
		register(path, handlers);
		register(path, controllers);
	}
	
	/**
	 * Registers handler and controller(s) for the specified path.
	 * @param <T> Controller class.
	 * @param path Path for which the handler and controller(s) are to be called.
	 * @param handler Handler.
	 * @param controllers Controller(s).
	 */
	@SafeVarargs
	public final <T extends Controller<Request, Response>> void register(String path, Handler<Request, Response> handler, Class<T>... controllers) {
		this.handlers.add(new PathHandler<Request, Response>(path, handler));
		register(path, controllers);
	}
	
	/**
	 * Registers two handlers and controller(s) for the specified path.
	 * @param <T> Controller class.
	 * @param path Path for which the handler and controller(s) are to be called.
	 * @param handler1 First handler.
	 * @param handler2 Second handler.
	 * @param controllers Controller(s).
	 */
	@SafeVarargs
	public final <T extends Controller<Request, Response>> void register(String path, Handler<Request, Response> handler1, Handler<Request, Response> handler2, Class<T>... controllers) {
		this.handlers.add(new PathHandler<Request, Response>(path, handler1));
		this.handlers.add(new PathHandler<Request, Response>(path, handler2));
		register(path, controllers);
	}
	
	/**
	 * Registers three handlers and controller(s) for the specified path.
	 * @param <T> Controller class.
	 * @param path Path for which the handlers and controller(s) are to be called.
	 * @param handler1 First handler.
	 * @param handler2 Second handler.
	 * @param handler3 Third controller.
	 * @param controllers Controller(s).
	 */
	@SafeVarargs
	public final <T extends Controller<Request, Response>> void register(String path, Handler<Request, Response> handler1, Handler<Request, Response> handler2, Handler<Request, Response> handler3, Class<T>... controllers) {
		this.handlers.add(new PathHandler<Request, Response>(path, handler1));
		this.handlers.add(new PathHandler<Request, Response>(path, handler2));
		this.handlers.add(new PathHandler<Request, Response>(path, handler3));
		register(path, controllers);
	}
	
	/**
	 * Registers four handlers and controller(s) for the specified path.
	 * @param <T> Controller class.
	 * @param path Path for which the handlers and controller(s) are to be called.
	 * @param handler1 First handler.
	 * @param handler2 Second handler.
	 * @param handler3 Third controller.
	 * @param handler4 Fourth controller.
	 * @param controllers Controller(s).
	 */
	@SafeVarargs
	public final <T extends Controller<Request, Response>> void register(String path, Handler<Request, Response> handler1, Handler<Request, Response> handler2, Handler<Request, Response> handler3, Handler<Request, Response> handler4, Class<T>... controllers) {
		this.handlers.add(new PathHandler<Request, Response>(path, handler1));
		this.handlers.add(new PathHandler<Request, Response>(path, handler2));
		this.handlers.add(new PathHandler<Request, Response>(path, handler3));
		this.handlers.add(new PathHandler<Request, Response>(path, handler4));
		register(path, controllers);
	}
	
	/**
	 * Registers five handlers and controller(s) for the specified path.
	 * @param <T> Controller class.
	 * @param path Path for which the handlers and controller(s) are to be called.
	 * @param handler1 First handler.
	 * @param handler2 Second handler.
	 * @param handler3 Third controller.
	 * @param handler4 Fourth controller.
	 * @param handler5 Fifth controller.
	 * @param controllers Controller(s).
	 */
	@SafeVarargs
	public final <T extends Controller<Request, Response>> void register(String path, Handler<Request, Response> handler1, Handler<Request, Response> handler2, Handler<Request, Response> handler3, Handler<Request, Response> handler4, Handler<Request, Response> handler5, Class<T>... controllers) {
		this.handlers.add(new PathHandler<Request, Response>(path, handler1));
		this.handlers.add(new PathHandler<Request, Response>(path, handler2));
		this.handlers.add(new PathHandler<Request, Response>(path, handler3));
		this.handlers.add(new PathHandler<Request, Response>(path, handler4));
		this.handlers.add(new PathHandler<Request, Response>(path, handler5));
		register(path, controllers);
	}
	
	/**
	 * Registers handler(s) for all paths.
	 * @param handlers Handler(s).
	 */
	@SafeVarargs
	public final void register(Handler<Request, Response>... handlers) {
		for (Handler<Request, Response> handler : handlers) {
			PathHandler<Request, Response> pathHandler = new PathHandler<Request, Response>(null, handler);
			this.handlers.add(pathHandler);
		}
	}
	
	/**
	 * Registers controller(s) for all paths.
	 * @param <T> Controller class.
	 * @param controllers Controller(s).
	 */
	@SafeVarargs
	public final <T extends Controller<Request, Response>> void register(Class<T>... controllers) {
		for (Class<T> controller : controllers) {
			ControllerInvoker<Request, Response> controllerInvoker = new ControllerInvoker<Request, Response>((Class<T>) controller);
			PathHandler<Request, Response> pathHandler = new PathHandler<Request, Response>(null, controllerInvoker);
			this.handlers.add(pathHandler);
		}
	}
	
	/**
	 * Registers list of handlers for all paths.
	 * @param handlers List of handlers.
	 */
	public final void register(List<Handler<Request, Response>> handlers) {
		Iterator<Handler<Request, Response>> iterator = handlers.iterator();
		
		while (iterator.hasNext()) {
			Handler<Request, Response> handler = iterator.next();
			PathHandler<Request, Response> pathHandler = new PathHandler<Request, Response>(null, handler);
			this.handlers.add(pathHandler);
		}
	}
	
	/**
	 * Registers list of handlers and controller(s) for all paths.
	 * @param <T> Controller class.
	 * @param handlers List of handlers.
	 * @param controllers Controller(s).
	 */
	@SafeVarargs
	public final <T extends Controller<Request, Response>> void register(List<Handler<Request, Response>> handlers, Class<T>... controllers) {
		register(null, handlers);
		registerControllers(null, controllers);
	}
	
	/**
	 * Registers list of handlers and list of controllers for all paths.
	 * @param <T> Controller class.
	 * @param handlers List of handlers.
	 * @param controllers List of controllers.
	 */
	public final <T extends Controller<Request, Response>> void register(List<Handler<Request, Response>> handlers, List<Class<T>> controllers) {
		register(null, handlers);
		registerListOfControllers(null, controllers);
	}
	
	/**
	 *  Registers array of handlers and controller(s) for all paths.
	 * @param <T> Controller class.
	 * @param handlers Array of handlers.
	 * @param controllers Controller(s).
	 */
	@SafeVarargs
	public final <T extends Controller<Request, Response>> void register(Handler<Request, Response>[] handlers, Class<T>... controllers) {
		register(null, handlers);
		registerControllers(null, controllers);
	}
	
	/**
	 * Registers handler and controller(s) for the specified path.
	 * @param <T> Controller class.
	 * @param handler Handler.
	 * @param controllers Controller(s).
	 */
	@SafeVarargs
	public final <T extends Controller<Request, Response>> void register(Handler<Request, Response> handler, Class<T>... controllers) {
		this.handlers.add(new PathHandler<Request, Response>(null, handler));
		registerControllers(null, controllers);
	}
	
	/**
	 * Registers two handlers and controller(s) for all paths.
	 * @param <T> Controller class.
	 * @param handler1 First handler.
	 * @param handler2 Second handler.
	 * @param controllers Controller(s).
	 */
	@SafeVarargs
	public final <T extends Controller<Request, Response>> void register(Handler<Request, Response> handler1, Handler<Request, Response> handler2, Class<T>... controllers) {
		this.handlers.add(new PathHandler<Request, Response>(null, handler1));
		this.handlers.add(new PathHandler<Request, Response>(null, handler2));
		registerControllers(null, controllers);
	}
	
	/**
	 * Registers three handlers and controller(s) for all paths.
	 * @param <T> Controller class.
	 * @param handler1 First handler.
	 * @param handler2 Second handler.
	 * @param handler3 Third controller.
	 * @param controllers Controller(s).
	 */
	@SafeVarargs
	public final <T extends Controller<Request, Response>> void register(Handler<Request, Response> handler1, Handler<Request, Response> handler2, Handler<Request, Response> handler3, Class<T>... controllers) {
		this.handlers.add(new PathHandler<Request, Response>(null, handler1));
		this.handlers.add(new PathHandler<Request, Response>(null, handler2));
		this.handlers.add(new PathHandler<Request, Response>(null, handler3));
		registerControllers(null, controllers);
	}
	
	/**
	 * Registers four handlers and controller(s) for all paths.
	 * @param <T> Controller class.
	 * @param handler1 First handler.
	 * @param handler2 Second handler.
	 * @param handler3 Third controller.
	 * @param handler4 Fourth controller.
	 * @param controllers Controller(s).
	 */
	@SafeVarargs
	public final <T extends Controller<Request, Response>> void register(Handler<Request, Response> handler1, Handler<Request, Response> handler2, Handler<Request, Response> handler3, Handler<Request, Response> handler4, Class<T>... controllers) {
		this.handlers.add(new PathHandler<Request, Response>(null, handler1));
		this.handlers.add(new PathHandler<Request, Response>(null, handler2));
		this.handlers.add(new PathHandler<Request, Response>(null, handler3));
		this.handlers.add(new PathHandler<Request, Response>(null, handler4));
		registerControllers(null, controllers);
	}
	
	/**
	 * Registers five handlers and controller(s) for all paths.
	 * @param <T> Controller class.
	 * @param handler1 First handler.
	 * @param handler2 Second handler.
	 * @param handler3 Third controller.
	 * @param handler4 Fourth controller.
	 * @param handler5 Fifth controller.
	 * @param controllers Controller(s).
	 */
	@SafeVarargs
	public final <T extends Controller<Request, Response>> void register(Handler<Request, Response> handler1, Handler<Request, Response> handler2, Handler<Request, Response> handler3, Handler<Request, Response> handler4, Handler<Request, Response> handler5, Class<T>... controllers) {
		this.handlers.add(new PathHandler<Request, Response>(null, handler1));
		this.handlers.add(new PathHandler<Request, Response>(null, handler2));
		this.handlers.add(new PathHandler<Request, Response>(null, handler3));
		this.handlers.add(new PathHandler<Request, Response>(null, handler4));
		this.handlers.add(new PathHandler<Request, Response>(null, handler5));
		registerControllers(null, controllers);
	}
	
	/**
	 * By default when error occurs in handle method of router, exception is thrown. This method registers error controller that is called instead of that.
	 * @param <T> Error controller class.
	 * @param errorController Error controller to be called when error occurs in handle method.
	 */
	public final <T extends ErrorController<Request, Response>> void registerErrorController(Class<T> errorController) {
		this.errorControllerClass = errorController;
	}
	
	@Override
	public final void setPathParams(Map<String, String> pathParams) {
		this.pathParams = pathParams;
	}
	
	/**
	 * Returns path parameter.
	 * @param paramName Name of path parameter.
	 * @return Value of path parameter or null.
	 */
	public String getPathParam(String paramName) {
		if (this.pathParams == null) return null;
		return pathParams.get(paramName);
	}
	
	@Override
	public boolean handle(Request request, Response response) throws Exception {
		return handle(request, response, 0);
	}
	
	/**
	 * Implementation of handle method. It is called for routers in handlers chain instead of normal handle method to be able to get the right part of request path.
	 * @param request Request to handle.
	 * @param response Response to handle.
	 * @param pathOffset Offset for request path. For example if request path is /info/about/authors and pathOffset is 2, the result path will be /authors.
	 * @return Determines whether handlers chain should continue or not.
	 * @throws Exception If error occurs and error controller is not registered, exception is thrown.
	 */
	final boolean handle(Request request, Response response, int pathOffset) throws Exception {
		try {
			String requestPath = getRequestPath(request);
			String[] requestPathArray = requestPath.split("/", -1);
			String[] pathArray = Arrays.copyOfRange(requestPathArray, pathOffset, requestPathArray.length);
			
			Iterator<PathHandler<Request, Response>> iterator = handlers.iterator();
			
			while (iterator.hasNext()) {
				PathHandler<Request, Response> pathHandler = iterator.next();
				
				if (pathHandler.matches(pathArray)) {
					Handler<Request, Response> handler = pathHandler.handler;
					
					Map<String, String> pathParams = pathHandler.createPathParams(pathArray);
					fillPathParams(pathParams);
					handler.setPathParams(pathParams);
					
					boolean continueChain;
					if (handler instanceof Router) {
						continueChain = ((Router<Request, Response>) handler).handle(request, response, pathOffset + pathHandler.getPathOffsetForNextRouter(pathArray));
					} else {
						continueChain = handler.handle(request, response);
					}
					
					if (!continueChain) return false;
				}
			}
			
			return true;
		} catch(Exception e) {
			if (errorControllerClass != null) {
				ErrorController<Request, Response> errorController = errorControllerClass.getConstructor(Exception.class).newInstance(e);
				return errorController.handle(request, response);
			} else {
				throw e;
			}
		}
	}
	
	@Override
	public final boolean matchesFullPath() {
		return false;
	}
	
	/**
	 * This method is called by implementation of handle method to get request path.
	 * @param request Request based on which should be determined request path.
	 * @return Request path without leading and trailing slashes. For example "info/about" (not <s>"/info/about/"</s>).
	 */
	protected abstract String getRequestPath(Request request);
	
	/**
	 * Helper method to register list of controllers.
	 * @param <T> Controller class.
	 * @param path Path for which the controllers are to be called.
	 * @param controllers List of controllers.
	 */
	private <T extends Controller<Request, Response>> void registerListOfControllers(String path, List<Class<T>> controllers) {
		Iterator<Class<T>> iterator = controllers.iterator();
		
		while (iterator.hasNext()) {
			Class<T> controller = iterator.next();
			ControllerInvoker<Request, Response> controllerInvoker = new ControllerInvoker<Request, Response>(controller);
			PathHandler<Request, Response> pathHandler = new PathHandler<Request, Response>(path, controllerInvoker);
			this.handlers.add(pathHandler);
		}
	}
	
	/**
	 * Helper method to register controller(s).
	 * @param <T> Controller class.
	 * @param path Path for which the controllers are to be called.
	 * @param controllers Controller(s).
	 */
	@SafeVarargs
	private final <T extends Controller<Request, Response>> void registerControllers(String path, Class<T>... controllers) {
		for (Class<T> controller : controllers) {
			ControllerInvoker<Request, Response> controllerInvoker = new ControllerInvoker<Request, Response>(controller);
			PathHandler<Request, Response> pathHandler = new PathHandler<Request, Response>(path, controllerInvoker);
			this.handlers.add(pathHandler);
		}
	}
	
	/**
	 * Fills passed map of path parameters with values from the router map of path parameters.
	 * @param pathParams
	 */
	private void fillPathParams(Map<String, String> pathParams) {
		if (this.pathParams == null) return;
		
		for (Map.Entry<String, String> paramName : this.pathParams.entrySet()) {
			pathParams.putIfAbsent(paramName.getKey(), paramName.getValue());
		}
	}
}