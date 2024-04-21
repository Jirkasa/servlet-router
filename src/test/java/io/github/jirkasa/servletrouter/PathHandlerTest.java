package io.github.jirkasa.servletrouter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.junit.jupiter.api.Test;

public class PathHandlerTest {
	@Test
	void itShouldMatchTheRequestPath() {
		PathHandler<ServletRequest, ServletResponse> pathHandler = new PathHandler<ServletRequest, ServletResponse>("test/something", new Handler<ServletRequest, ServletResponse>() {
			@Override
			public boolean handle(ServletRequest request, ServletResponse response) throws Exception {
				return false;
			}

			@Override
			public void setPathParams(Map<String, String> pathParams) {}

			@Override
			public boolean matchesFullPath() {
				return true;
			}
		});
		
		String[] path = {"test", "something"};
		
		assertTrue(pathHandler.matches(path));
	}
	
	@Test
	void itShouldMatchTheRequestPathEvenWhenPathStartsWithSlash() {
		PathHandler<ServletRequest, ServletResponse> pathHandler = new PathHandler<ServletRequest, ServletResponse>("/test/something", new Handler<ServletRequest, ServletResponse>() {
			@Override
			public boolean handle(ServletRequest request, ServletResponse response) throws Exception {
				return false;
			}

			@Override
			public void setPathParams(Map<String, String> pathParams) {}

			@Override
			public boolean matchesFullPath() {
				return true;
			}
		});
		
		String[] path = {"test", "something"};
		
		assertTrue(pathHandler.matches(path));
	}
	
	@Test
	void itShouldMatchTheRequestPathEvenWhenPathEndsWithSlash() {
		PathHandler<ServletRequest, ServletResponse> pathHandler = new PathHandler<ServletRequest, ServletResponse>("test/something/", new Handler<ServletRequest, ServletResponse>() {
			@Override
			public boolean handle(ServletRequest request, ServletResponse response) throws Exception {
				return false;
			}

			@Override
			public void setPathParams(Map<String, String> pathParams) {}

			@Override
			public boolean matchesFullPath() {
				return true;
			}
		});
		
		String[] path = {"test", "something"};
		
		assertTrue(pathHandler.matches(path));
	}
	
	@Test
	void itShouldMatchTheRequestPathEvenWhenPathStartsAndEndsWithSlash() {
		PathHandler<ServletRequest, ServletResponse> pathHandler = new PathHandler<ServletRequest, ServletResponse>("/test/something/", new Handler<ServletRequest, ServletResponse>() {
			@Override
			public boolean handle(ServletRequest request, ServletResponse response) throws Exception {
				return false;
			}

			@Override
			public void setPathParams(Map<String, String> pathParams) {}

			@Override
			public boolean matchesFullPath() {
				return true;
			}
		});
		
		String[] path = {"test", "something"};
		
		assertTrue(pathHandler.matches(path));
	}
	
	@Test
	void itShouldNotMatchTheRequestPathWhenPathStartsWithTwoSlashes() {
		PathHandler<ServletRequest, ServletResponse> pathHandler = new PathHandler<ServletRequest, ServletResponse>("//test/something", new Handler<ServletRequest, ServletResponse>() {
			@Override
			public boolean handle(ServletRequest request, ServletResponse response) throws Exception {
				return false;
			}

			@Override
			public void setPathParams(Map<String, String> pathParams) {}

			@Override
			public boolean matchesFullPath() {
				return true;
			}
		});
		
		String[] path = {"test", "something"};
		
		assertFalse(pathHandler.matches(path));
	}
	
	@Test
	void itShouldMatchAnyRequestPathWhenPathIsNull() {
		PathHandler<ServletRequest, ServletResponse> pathHandler = new PathHandler<ServletRequest, ServletResponse>(null, new Handler<ServletRequest, ServletResponse>() {
			@Override
			public boolean handle(ServletRequest request, ServletResponse response) throws Exception {
				return false;
			}

			@Override
			public void setPathParams(Map<String, String> pathParams) {}

			@Override
			public boolean matchesFullPath() {
				return true;
			}
		});
		
		String[] path1 = {"test", "something"};
		String[] path2 = {"anything"};
		
		
		assertTrue(pathHandler.matches(path1));
		assertTrue(pathHandler.matches(path2));
	}
	
	@Test
	void itShouldNotMatchRequestPathIfPathIsDifferent() {
		PathHandler<ServletRequest, ServletResponse> pathHandler = new PathHandler<ServletRequest, ServletResponse>("test/something", new Handler<ServletRequest, ServletResponse>() {
			@Override
			public boolean handle(ServletRequest request, ServletResponse response) throws Exception {
				return false;
			}

			@Override
			public void setPathParams(Map<String, String> pathParams) {}

			@Override
			public boolean matchesFullPath() {
				return true;
			}
		});
		
		String[] path = {"best", "something"};
		
		assertFalse(pathHandler.matches(path));
	}
	
	@Test
	void itShouldNotMatchRequestPathIfPathStartsWithRequestPathButHandlerShouldMatchFullPath() {
		PathHandler<ServletRequest, ServletResponse> pathHandler = new PathHandler<ServletRequest, ServletResponse>("test/something", new Handler<ServletRequest, ServletResponse>() {
			@Override
			public boolean handle(ServletRequest request, ServletResponse response) throws Exception {
				return false;
			}

			@Override
			public void setPathParams(Map<String, String> pathParams) {}

			@Override
			public boolean matchesFullPath() {
				return true;
			}
		});
		
		String[] path = {"test", "something", "else"};
		
		assertFalse(pathHandler.matches(path));
	}
	
	@Test
	void itShouldMatchRequestPathIfPathStartsWithRequestPathAndHandlerDoesNotMatchFullPath() {
		PathHandler<ServletRequest, ServletResponse> pathHandler = new PathHandler<ServletRequest, ServletResponse>("test/something", new Handler<ServletRequest, ServletResponse>() {
			@Override
			public boolean handle(ServletRequest request, ServletResponse response) throws Exception {
				return false;
			}

			@Override
			public void setPathParams(Map<String, String> pathParams) {}

			@Override
			public boolean matchesFullPath() {
				return false;
			}
		});
		
		String[] path = {"test", "something", "else"};
		
		assertTrue(pathHandler.matches(path));
	}
	
	@Test
	void itShouldMatchRequestPathWithPathParameter() {
		PathHandler<ServletRequest, ServletResponse> pathHandler = new PathHandler<ServletRequest, ServletResponse>("user/:id", new Handler<ServletRequest, ServletResponse>() {
			@Override
			public boolean handle(ServletRequest request, ServletResponse response) throws Exception {
				return false;
			}

			@Override
			public void setPathParams(Map<String, String> pathParams) {}

			@Override
			public boolean matchesFullPath() {
				return true;
			}
		});
		
		String[] path = {"user", "123456789"};
		
		assertTrue(pathHandler.matches(path));
	}
	
	@Test
	void itShouldMatchRequestPathWithTwoPathParameters() {
		PathHandler<ServletRequest, ServletResponse> pathHandler = new PathHandler<ServletRequest, ServletResponse>("user/:category/:id", new Handler<ServletRequest, ServletResponse>() {
			@Override
			public boolean handle(ServletRequest request, ServletResponse response) throws Exception {
				return false;
			}

			@Override
			public void setPathParams(Map<String, String> pathParams) {}

			@Override
			public boolean matchesFullPath() {
				return true;
			}
		});
		
		String[] path = {"user", "developer", "123456789"};
		
		assertTrue(pathHandler.matches(path));
	}
	
	@Test
	void itShouldReturnOffset1ForNextRouter() {
		PathHandler<ServletRequest, ServletResponse> pathHandler = new PathHandler<ServletRequest, ServletResponse>("app", new Handler<ServletRequest, ServletResponse>() {
			@Override
			public boolean handle(ServletRequest request, ServletResponse response) throws Exception {
				return false;
			}

			@Override
			public void setPathParams(Map<String, String> pathParams) {}

			@Override
			public boolean matchesFullPath() {
				return true;
			}
		});
		
		String[] path = {"app", "about", "router"};
		
		assertEquals(pathHandler.getPathOffsetForNextRouter(path), 1);
	}
	
	@Test
	void itShouldReturnOffset2ForNextRouter() {
		PathHandler<ServletRequest, ServletResponse> pathHandler = new PathHandler<ServletRequest, ServletResponse>("app/about", new Handler<ServletRequest, ServletResponse>() {
			@Override
			public boolean handle(ServletRequest request, ServletResponse response) throws Exception {
				return false;
			}

			@Override
			public void setPathParams(Map<String, String> pathParams) {}

			@Override
			public boolean matchesFullPath() {
				return true;
			}
		});
		
		String[] path = {"app", "about", "router"};
		
		assertEquals(pathHandler.getPathOffsetForNextRouter(path), 2);
	}
	
	@Test
	void idShouldReturnMinus1IfPathIsGreaterThanRequestPath() {
		PathHandler<ServletRequest, ServletResponse> pathHandler = new PathHandler<ServletRequest, ServletResponse>("app/about/router/servlet", new Handler<ServletRequest, ServletResponse>() {
			@Override
			public boolean handle(ServletRequest request, ServletResponse response) throws Exception {
				return false;
			}

			@Override
			public void setPathParams(Map<String, String> pathParams) {}

			@Override
			public boolean matchesFullPath() {
				return true;
			}
		});
		
		String[] path = {"app", "about", "router"};
		
		assertEquals(pathHandler.getPathOffsetForNextRouter(path), -1);
	}
	
	@Test
	void itShouldReturnOffset2ForNextRouterWithPathParam() {
		PathHandler<ServletRequest, ServletResponse> pathHandler = new PathHandler<ServletRequest, ServletResponse>("app/:category", new Handler<ServletRequest, ServletResponse>() {
			@Override
			public boolean handle(ServletRequest request, ServletResponse response) throws Exception {
				return false;
			}

			@Override
			public void setPathParams(Map<String, String> pathParams) {}

			@Override
			public boolean matchesFullPath() {
				return true;
			}
		});
		
		String[] path = {"app", "about", "router"};
		
		assertEquals(pathHandler.getPathOffsetForNextRouter(path), 2);
	}
	
	@Test
	void itShouldReturnOffset0ForNextRouterIfPathIsNull() {
		PathHandler<ServletRequest, ServletResponse> pathHandler = new PathHandler<ServletRequest, ServletResponse>(null, new Handler<ServletRequest, ServletResponse>() {
			@Override
			public boolean handle(ServletRequest request, ServletResponse response) throws Exception {
				return false;
			}

			@Override
			public void setPathParams(Map<String, String> pathParams) {}

			@Override
			public boolean matchesFullPath() {
				return true;
			}
		});
		
		String[] path = {"app", "about", "router"};
		
		assertEquals(pathHandler.getPathOffsetForNextRouter(path), 0);
	}
	
	@Test
	void itShouldCreateEmptyPathParamsMapIfPathIsNull() {
		PathHandler<ServletRequest, ServletResponse> pathHandler = new PathHandler<ServletRequest, ServletResponse>(null, new Handler<ServletRequest, ServletResponse>() {
			@Override
			public boolean handle(ServletRequest request, ServletResponse response) throws Exception {
				return false;
			}

			@Override
			public void setPathParams(Map<String, String> pathParams) {}

			@Override
			public boolean matchesFullPath() {
				return true;
			}
		});
		
		String[] path = {"app", "about", "router"};
		
		assertEquals(pathHandler.createPathParams(path).size(), 0);
	}
	
	@Test
	void itShouldCreateEmptyPathParamsMapIfPathDoesNotContainPathParams() {
		PathHandler<ServletRequest, ServletResponse> pathHandler = new PathHandler<ServletRequest, ServletResponse>("app/about/router", new Handler<ServletRequest, ServletResponse>() {
			@Override
			public boolean handle(ServletRequest request, ServletResponse response) throws Exception {
				return false;
			}

			@Override
			public void setPathParams(Map<String, String> pathParams) {}

			@Override
			public boolean matchesFullPath() {
				return true;
			}
		});
		
		String[] path = {"app", "about", "router"};
		
		assertEquals(pathHandler.createPathParams(path).size(), 0);
	}
	
	@Test
	void itShouldCreateMapWithPathParamNamedTest() {
		PathHandler<ServletRequest, ServletResponse> pathHandler = new PathHandler<ServletRequest, ServletResponse>("app/about/:test", new Handler<ServletRequest, ServletResponse>() {
			@Override
			public boolean handle(ServletRequest request, ServletResponse response) throws Exception {
				return false;
			}

			@Override
			public void setPathParams(Map<String, String> pathParams) {}

			@Override
			public boolean matchesFullPath() {
				return true;
			}
		});
		
		String[] path = {"app", "about", "router"};
		
		Map<String, String> pathParams = pathHandler.createPathParams(path);
		
		assertEquals(pathParams.size(), 1);
		assertEquals(pathParams.get("test"), "router");
	}
	
	@Test
	void itShouldCreateMapWithPathParamsNamedCategoryAndTest() {
		PathHandler<ServletRequest, ServletResponse> pathHandler = new PathHandler<ServletRequest, ServletResponse>("app/:category/:test", new Handler<ServletRequest, ServletResponse>() {
			@Override
			public boolean handle(ServletRequest request, ServletResponse response) throws Exception {
				return false;
			}

			@Override
			public void setPathParams(Map<String, String> pathParams) {}

			@Override
			public boolean matchesFullPath() {
				return true;
			}
		});
		
		String[] path = {"app", "about", "router"};
		
		Map<String, String> pathParams = pathHandler.createPathParams(path);
		
		assertEquals(pathParams.size(), 2);
		assertEquals(pathParams.get("category"), "about");
		assertEquals(pathParams.get("test"), "router");
	}
}