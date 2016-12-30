package com.app.filter;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Calendar;

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

public class UserSessionFilter extends SessionFilter implements Filter {

	private static final Logger logger = LoggerFactory.getLogger(UserSessionFilter.class);

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest httpServletRequest = ((HttpServletRequest) request);

		HttpServletResponse httpServletResponse = ((HttpServletResponse) response);

		get(httpServletRequest);

		String sessionId = getSessionId();
		Long userId = getUserId();

		logger.info("SessionId = " + sessionId + "  UserId = " + userId);

		if (sessionId == null || userId == null) {
			httpServletResponse.sendRedirect("/med-1/login");
			return;
		}

		Session session = Registry.sessionFinder().findById(userId);

		String path = httpServletRequest.getRequestURL().toString();

		if (session != null && sessionId.equals(session.getSessionHash()) && session.isSessionIsActive()
				&& session.getUser().getUserType() == getUserTypeFromUrl(path)) {

			logger.info(session.toString());

			Timestamp startSession = new Timestamp(Calendar.getInstance().getTime().getTime());

			session.setSessionDate(startSession);
			session.update();

			chain.doFilter(request, response);
		} else {
			httpServletResponse.sendRedirect("/med-1/noacces");
			return;
		}

	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
	}

}
