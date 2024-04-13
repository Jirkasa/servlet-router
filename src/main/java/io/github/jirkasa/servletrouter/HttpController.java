package io.github.jirkasa.servletrouter;

import java.lang.reflect.Method;
import java.util.Enumeration;

import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class HttpController extends Controller<HttpServletRequest, HttpServletResponse> {
	private static final String METHOD_GET = "GET";
    private static final String METHOD_POST = "POST";
    private static final String METHOD_PUT = "PUT";
    private static final String METHOD_PATCH = "PATCH";
    private static final String METHOD_DELETE = "DELETE";
    private static final String METHOD_HEAD = "HEAD";
    private static final String METHOD_OPTIONS = "OPTIONS";
    private static final String METHOD_TRACE = "TRACE";
    
	private boolean continueHandlersChain = false;
	protected boolean skipUnimplementedMethods = false;
	
	public HttpController(ServletContext servletContext) {
		super(servletContext);
	}

	@Override
	public boolean handle(HttpServletRequest request, HttpServletResponse response) throws Exception { // pro 404 je potřeba přepsat tuto metodu
		String method = request.getMethod();
		
		switch(method) {
			case METHOD_GET:
				handleGet(request, response);
				break;
			case METHOD_POST:
				handlePost(request, response);
				break;
			case METHOD_PUT:
				handlePut(request, response);
				break;
			case METHOD_PATCH:
				handlePatch(request, response);
				break;
			case METHOD_DELETE:
				handleDelete(request, response);
				break;
			case METHOD_HEAD:
				handleHead(request, response);
				break;
			case METHOD_OPTIONS:
				handleOptions(request, response);
				break;
			case METHOD_TRACE:
				handleTrace(request, response);
				break;
			default:
				handleUnknownMethod(request, response);
		}
		
		return continueHandlersChain;
	}
	
	protected void handleGet(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (skipUnimplementedMethods) {
			continueHandlersChain();
		} else {
			continueHandlersChain = false;
			String protocol = request.getProtocol();
	        String msg = "GET method is not supported.";
	        if (protocol.endsWith("1.1")) {
	        	response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, msg);
	        } else {
	        	response.sendError(HttpServletResponse.SC_BAD_REQUEST, msg);
	        }
		}
	}
	
	protected void handlePost(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (skipUnimplementedMethods) {
			continueHandlersChain();
		} else {
			continueHandlersChain = false;
			String protocol = request.getProtocol();
			String msg = "POST method is not supported.";
	        if (protocol.endsWith("1.1")) {
	        	response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, msg);
	        } else {
	        	response.sendError(HttpServletResponse.SC_BAD_REQUEST, msg);
	        }
		}
	}
	
	protected void handlePut(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (skipUnimplementedMethods) {
			continueHandlersChain();
		} else {
			continueHandlersChain = false;
			String protocol = request.getProtocol();
			String msg = "PUT method is not supported.";
	        if (protocol.endsWith("1.1")) {
	        	response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, msg);
	        } else {
	        	response.sendError(HttpServletResponse.SC_BAD_REQUEST, msg);
	        }
		}
	}
	
	protected void handlePatch(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (skipUnimplementedMethods) {
			continueHandlersChain();
		} else {
			continueHandlersChain = false;
			String protocol = request.getProtocol();
			String msg = "PATCH method is not supported.";
	        if (protocol.endsWith("1.1")) {
	        	response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, msg);
	        } else {
	        	response.sendError(HttpServletResponse.SC_BAD_REQUEST, msg);
	        }
		}
	}
	
	protected void handleDelete(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (skipUnimplementedMethods) {
			continueHandlersChain();
		} else {
			continueHandlersChain = false;
			String protocol = request.getProtocol();
			String msg = "DELETE method is not supported.";
	        if (protocol.endsWith("1.1")) {
	        	response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, msg);
	        } else {
	        	response.sendError(HttpServletResponse.SC_BAD_REQUEST, msg);
	        }
		}
	}
	
	protected void handleHead(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (skipUnimplementedMethods) {
			continueHandlersChain();
		} else {
			continueHandlersChain = false;
			String protocol = request.getProtocol();
			String msg = "HEAD method is not supported.";
	        if (protocol.endsWith("1.1")) {
	        	response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, msg);
	        } else {
	        	response.sendError(HttpServletResponse.SC_BAD_REQUEST, msg);
	        }
		}
	}
	
	protected void handleOptions(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Method[] methods = getAllDeclaredMethods(this.getClass());
        
        boolean ALLOW_GET = false;
        boolean ALLOW_HEAD = false;
        boolean ALLOW_POST = false;
        boolean ALLOW_PUT = false;
        boolean ALLOW_PATCH = false;
        boolean ALLOW_DELETE = false;
        boolean ALLOW_TRACE = true;
        boolean ALLOW_OPTIONS = true;
        
        for (int i=0; i<methods.length; i++) {
            String methodName = methods[i].getName();
            
            if (methodName.equals("handleGet")) {
                ALLOW_GET = true;
            } else if (methodName.equals("handleHead")) {
                ALLOW_HEAD = true;
            } else if (methodName.equals("handlePost")) {
                ALLOW_POST = true;
            } else if (methodName.equals("handlePut")) {
                ALLOW_PUT = true;
            } else if (methodName.equals("handlePatch")) {
            	ALLOW_PATCH = true;
            } else if (methodName.equals("handleDelete")) {
                ALLOW_DELETE = true;
            }
            
        }
        
        // we know "allow" is not null as ALLOW_OPTIONS = true
        // when this method is invoked
        StringBuilder allow = new StringBuilder();
        if (ALLOW_GET) {
            allow.append(METHOD_GET);
        }
        if (ALLOW_HEAD) {
            if (allow.length() > 0) {
                allow.append(", ");
            }
            allow.append(METHOD_HEAD);
        }
        if (ALLOW_POST) {
            if (allow.length() > 0) {
                allow.append(", ");
            }
            allow.append(METHOD_POST);
        }
        if (ALLOW_PUT) {
            if (allow.length() > 0) {
                allow.append(", ");
            }
            allow.append(METHOD_PUT);
        }
        if (ALLOW_PATCH) {
            if (allow.length() > 0) {
                allow.append(", ");
            }
            allow.append(METHOD_PATCH);
        }
        if (ALLOW_DELETE) {
            if (allow.length() > 0) {
                allow.append(", ");
            }
            allow.append(METHOD_DELETE);
        }
        if (ALLOW_TRACE) {
            if (allow.length() > 0) {
                allow.append(", ");
            }
            allow.append(METHOD_TRACE);
        }
        if (ALLOW_OPTIONS) {
            if (allow.length() > 0) {
                allow.append(", ");
            }
            allow.append(METHOD_OPTIONS);
        }
        
        response.setHeader("Allow", allow.toString());
	}
	
	protected void handleTrace(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int responseLength;

        String CRLF = "\r\n";
        StringBuilder buffer = new StringBuilder("TRACE ").append(request.getRequestURI())
            .append(" ").append(request.getProtocol());

        Enumeration<String> reqHeaderEnum = request.getHeaderNames();

        while( reqHeaderEnum.hasMoreElements() ) {
            String headerName = reqHeaderEnum.nextElement();
            buffer.append(CRLF).append(headerName).append(": ").append(request);
        }

        buffer.append(CRLF);

        responseLength = buffer.length();

        response.setContentType("message/http");
        response.setContentLength(responseLength);
        ServletOutputStream out = response.getOutputStream();
        out.print(buffer.toString());
	}
	
	// určuje, jestli se má pokračovat na další handlery/controllery
	protected void continueHandlersChain() {
		continueHandlersChain = true;
	}
	
	private void handleUnknownMethod(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (skipUnimplementedMethods) {
			continueHandlersChain();
		} else {
			continueHandlersChain = false;
			String protocol = request.getProtocol();
			String msg = "Method is not supported.";
	        if (protocol.endsWith("1.1")) {
	        	response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, msg);
	        } else {
	        	response.sendError(HttpServletResponse.SC_BAD_REQUEST, msg);
	        }
		}
	}
	
	// todo - metodu na získání BASE URL
	
	private Method[] getAllDeclaredMethods(Class<? extends HttpController> c) {

        Class<?> clazz = c;
        Method[] allMethods = null;

        while (!clazz.equals(HttpController.class)) {
            Method[] thisMethods = clazz.getDeclaredMethods();
            if (allMethods != null && allMethods.length > 0) {
                Method[] subClassMethods = allMethods;
                allMethods =
                    new Method[thisMethods.length + subClassMethods.length];
                System.arraycopy(thisMethods, 0, allMethods, 0,
                                 thisMethods.length);
                System.arraycopy(subClassMethods, 0, allMethods, thisMethods.length,
                                 subClassMethods.length);
            } else {
                allMethods = thisMethods;
            }

            clazz = clazz.getSuperclass();
        }

        return ((allMethods != null) ? allMethods : new Method[0]);
    }
}