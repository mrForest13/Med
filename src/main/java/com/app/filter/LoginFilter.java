package com.app.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.app.model.session.Session;
import com.app.registry.Registry;

public class LoginFilter extends SessionFilter implements Filter {

	private static final Logger logger = LoggerFactory.getLogger(LoginFilter.class);

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest httpServletRequest = ((HttpServletRequest) request);

		HttpServletResponse httpServletResponse = ((HttpServletResponse) response);

		String path = httpServletRequest.getContextPath();

		get(httpServletRequest);

		String sessionId = getSessionId();
		Long userId = getUserId();

		logger.info("SessionId = " + sessionId + "  UserId = " + userId);

		if (sessionId == null || userId == null) {
			chain.doFilter(request, response);
			return;
		}

		Session session = Registry.sessionFinder().findById(userId);

		if (session != null && session.isSessionIsActive()) {
			request.setAttribute("type", session.getUser().getUserType());
			httpServletRequest.setAttribute("userId", userId);
		} else {
			request.setAttribute("type", -1);
			httpServletRequest.setAttribute("userId", userId);
		}

		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

}
