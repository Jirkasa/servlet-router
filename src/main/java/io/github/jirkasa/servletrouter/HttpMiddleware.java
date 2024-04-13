package io.github.jirkasa.servletrouter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class HttpMiddleware extends Middleware<HttpServletRequest, HttpServletResponse> {}