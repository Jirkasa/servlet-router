package io.github.jirkasa.servletrouter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * {@link io.github.jirkasa.servletrouter.Middleware Middleware} that uses HttpServletRequest and HttpServletResponse.
 */
public abstract class HttpMiddleware extends Middleware<HttpServletRequest, HttpServletResponse> {}