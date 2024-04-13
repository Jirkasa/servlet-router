package io.github.jirkasa.servletrouter;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public abstract class Router<Request extends ServletRequest, Response extends ServletResponse> implements Handler<Request, Response> {
	protected final ServletContext servletContext;
	
	private List<PathHandler<Request, Response>> handlers = new LinkedList<PathHandler<Request, Response>>();
	private Class<? extends ErrorController<Request, Response>> errorControllerClass = null;
	
	private Map<String, String> pathParams = null;
	
	public Router(ServletContext servletContext) {
		this.servletContext = servletContext;
	}
	
	@SafeVarargs
	public final void register(String path, Handler<Request, Response>... handlers) {
		for (Handler<Request, Response> handler : handlers) {
			PathHandler<Request, Response> pathHandler = new PathHandler<Request, Response>(path, handler);
			this.handlers.add(pathHandler);
		}
	}
	
	@SafeVarargs
	public final <T extends Controller<Request, Response>> void register(String path, Class<T>... controllers) {
		registerControllers(path, controllers);
	}
	
	public final void register(String path, List<Handler<Request, Response>> handlers) {
		Iterator<Handler<Request, Response>> iterator = handlers.iterator();
		
		while (iterator.hasNext()) {
			Handler<Request, Response> handler = iterator.next();
			PathHandler<Request, Response> pathHandler = new PathHandler<Request, Response>(path, handler);
			this.handlers.add(pathHandler);
		}
	}
	
	@SafeVarargs
	public final <T extends Controller<Request, Response>> void register(String path, List<Handler<Request, Response>> handlers, Class<T>... controllers) {
		register(path, handlers);
		register(path, controllers);
	}
	
	public final <T extends Controller<Request, Response>> void register(String path, List<Handler<Request, Response>> handlers, List<Class<T>> controllers) {
		register(path, handlers);
		registerListOfControllers(path, controllers);
	}
	
	@SafeVarargs
	public final <T extends Controller<Request, Response>> void register(String path, Handler<Request, Response>[] handlers, Class<T>... controllers) {
		register(path, handlers);
		register(path, controllers);
	}
	
	@SafeVarargs
	public final <T extends Controller<Request, Response>> void register(String path, Handler<Request, Response> handler, Class<T>... controllers) {
		this.handlers.add(new PathHandler<Request, Response>(path, handler));
		register(path, controllers);
	}
	
	@SafeVarargs
	public final <T extends Controller<Request, Response>> void register(String path, Handler<Request, Response> handler1, Handler<Request, Response> handler2, Class<T>... controllers) {
		this.handlers.add(new PathHandler<Request, Response>(path, handler1));
		this.handlers.add(new PathHandler<Request, Response>(path, handler2));
		register(path, controllers);
	}
	
	@SafeVarargs
	public final <T extends Controller<Request, Response>> void register(String path, Handler<Request, Response> handler1, Handler<Request, Response> handler2, Handler<Request, Response> handler3, Class<T>... controllers) {
		this.handlers.add(new PathHandler<Request, Response>(path, handler1));
		this.handlers.add(new PathHandler<Request, Response>(path, handler2));
		this.handlers.add(new PathHandler<Request, Response>(path, handler3));
		register(path, controllers);
	}
	
	@SafeVarargs
	public final <T extends Controller<Request, Response>> void register(String path, Handler<Request, Response> handler1, Handler<Request, Response> handler2, Handler<Request, Response> handler3, Handler<Request, Response> handler4, Class<T>... controllers) {
		this.handlers.add(new PathHandler<Request, Response>(path, handler1));
		this.handlers.add(new PathHandler<Request, Response>(path, handler2));
		this.handlers.add(new PathHandler<Request, Response>(path, handler3));
		this.handlers.add(new PathHandler<Request, Response>(path, handler4));
		register(path, controllers);
	}
	
	@SafeVarargs
	public final <T extends Controller<Request, Response>> void register(String path, Handler<Request, Response> handler1, Handler<Request, Response> handler2, Handler<Request, Response> handler3, Handler<Request, Response> handler4, Handler<Request, Response> handler5, Class<T>... controllers) {
		this.handlers.add(new PathHandler<Request, Response>(path, handler1));
		this.handlers.add(new PathHandler<Request, Response>(path, handler2));
		this.handlers.add(new PathHandler<Request, Response>(path, handler3));
		this.handlers.add(new PathHandler<Request, Response>(path, handler4));
		this.handlers.add(new PathHandler<Request, Response>(path, handler5));
		register(path, controllers);
	}
	
	@SafeVarargs
	public final void register(Handler<Request, Response>... handlers) {
		for (Handler<Request, Response> handler : handlers) {
			PathHandler<Request, Response> pathHandler = new PathHandler<Request, Response>(null, handler);
			this.handlers.add(pathHandler);
		}
	}
	
	@SafeVarargs
	public final <T extends Controller<Request, Response>> void register(Class<T>... controllers) {
		for (Class<T> controller : controllers) {
			ControllerInvoker<Request, Response> controllerInvoker = new ControllerInvoker<Request, Response>((Class<T>) controller);
			PathHandler<Request, Response> pathHandler = new PathHandler<Request, Response>(null, controllerInvoker);
			this.handlers.add(pathHandler);
		}
	}
	
	public final void register(List<Handler<Request, Response>> handlers) {
		Iterator<Handler<Request, Response>> iterator = handlers.iterator();
		
		while (iterator.hasNext()) {
			Handler<Request, Response> handler = iterator.next();
			PathHandler<Request, Response> pathHandler = new PathHandler<Request, Response>(null, handler);
			this.handlers.add(pathHandler);
		}
	}
	
	@SafeVarargs
	public final <T extends Controller<Request, Response>> void register(List<Handler<Request, Response>> handlers, Class<T>... controllers) {
		register(null, handlers);
		registerControllers(null, controllers);
	}
	
	public final <T extends Controller<Request, Response>> void register(List<Handler<Request, Response>> handlers, List<Class<T>> controllers) {
		register(null, handlers);
		registerListOfControllers(null, controllers);
	}
	
	@SafeVarargs
	public final <T extends Controller<Request, Response>> void register(Handler<Request, Response>[] handlers, Class<T>... controllers) {
		register(null, handlers);
		registerControllers(null, controllers);
	}
	
	@SafeVarargs
	public final <T extends Controller<Request, Response>> void register(Handler<Request, Response> handler, Class<T>... controllers) {
		this.handlers.add(new PathHandler<Request, Response>(null, handler));
		registerControllers(null, controllers);
	}
	
	@SafeVarargs
	public final <T extends Controller<Request, Response>> void register(Handler<Request, Response> handler1, Handler<Request, Response> handler2, Class<T>... controllers) {
		this.handlers.add(new PathHandler<Request, Response>(null, handler1));
		this.handlers.add(new PathHandler<Request, Response>(null, handler2));
		registerControllers(null, controllers);
	}
	
	@SafeVarargs
	public final <T extends Controller<Request, Response>> void register(Handler<Request, Response> handler1, Handler<Request, Response> handler2, Handler<Request, Response> handler3, Class<T>... controllers) {
		this.handlers.add(new PathHandler<Request, Response>(null, handler1));
		this.handlers.add(new PathHandler<Request, Response>(null, handler2));
		this.handlers.add(new PathHandler<Request, Response>(null, handler3));
		registerControllers(null, controllers);
	}
	
	@SafeVarargs
	public final <T extends Controller<Request, Response>> void register(Handler<Request, Response> handler1, Handler<Request, Response> handler2, Handler<Request, Response> handler3, Handler<Request, Response> handler4, Class<T>... controllers) {
		this.handlers.add(new PathHandler<Request, Response>(null, handler1));
		this.handlers.add(new PathHandler<Request, Response>(null, handler2));
		this.handlers.add(new PathHandler<Request, Response>(null, handler3));
		this.handlers.add(new PathHandler<Request, Response>(null, handler4));
		registerControllers(null, controllers);
	}
	
	@SafeVarargs
	public final <T extends Controller<Request, Response>> void register(Handler<Request, Response> handler1, Handler<Request, Response> handler2, Handler<Request, Response> handler3, Handler<Request, Response> handler4, Handler<Request, Response> handler5, Class<T>... controllers) {
		this.handlers.add(new PathHandler<Request, Response>(null, handler1));
		this.handlers.add(new PathHandler<Request, Response>(null, handler2));
		this.handlers.add(new PathHandler<Request, Response>(null, handler3));
		this.handlers.add(new PathHandler<Request, Response>(null, handler4));
		this.handlers.add(new PathHandler<Request, Response>(null, handler5));
		registerControllers(null, controllers);
	}
	
	public final <T extends ErrorController<Request, Response>> void registerErrorController(Class<T> errorController) {
		this.errorControllerClass = errorController;
	}
	
	@Override
	public final void setPathParams(Map<String, String> pathParams) {
		this.pathParams = pathParams;
	}
	
	// dá se to použít, pokud se dělá nějaký custom router, který potřebuje v přepsané handle metodě přístup k path parametrům
	public String getPathParam(String paramName) {
		if (this.pathParams == null) return null;
		return pathParams.get(paramName);
	}
	
	@Override
	public boolean handle(Request request, Response response) throws Exception {
		return handle(request, response, 0);
	}
	
	public final boolean handle(Request request, Response response, int pathOffset) throws Exception {
		try {
			String requestPath = getPath(request);
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
				ErrorController<Request, Response> errorController = errorControllerClass.getConstructor(ServletContext.class, Exception.class).newInstance(request.getServletContext(), e);
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
	
	// cesta musí být bez počátečního a koncového lomítka (pro index je prázdný řetězec)
	protected abstract String getPath(Request request);
	
	private <T extends Controller<Request, Response>> void registerListOfControllers(String path, List<Class<T>> controllers) {
		Iterator<Class<T>> iterator = controllers.iterator();
		
		while (iterator.hasNext()) {
			Class<T> controller = iterator.next();
			ControllerInvoker<Request, Response> controllerInvoker = new ControllerInvoker<Request, Response>(controller);
			PathHandler<Request, Response> pathHandler = new PathHandler<Request, Response>(path, controllerInvoker);
			this.handlers.add(pathHandler);
		}
	}
	
	@SafeVarargs
	private final <T extends Controller<Request, Response>> void registerControllers(String path, Class<T>... controllers) {
		for (Class<T> controller : controllers) {
			ControllerInvoker<Request, Response> controllerInvoker = new ControllerInvoker<Request, Response>(controller);
			PathHandler<Request, Response> pathHandler = new PathHandler<Request, Response>(path, controllerInvoker);
			this.handlers.add(pathHandler);
		}
	}
	
	private void fillPathParams(Map<String, String> pathParams) {
		if (this.pathParams == null) return;
		
		for (Map.Entry<String, String> paramName : this.pathParams.entrySet()) {
			pathParams.putIfAbsent(paramName.getKey(), paramName.getValue());
		}
	}
}