package com.app.filter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.app.med.HomeController;

public abstract class SessionFilter {

	private static final Logger logger = LoggerFactory.getLogger(SessionFilter.class);
	
	private String sessionId;
	private Long userId;
	
	public Long getUserTypeFromUrl(String url) {
		
		logger.info(url);
		
		String[] arr = url.split("/");
		
		logger.info(arr[4]);
		
		switch (arr[4]) {
		case "user":
			return 0L;
		case "doctor":
			return 1L;
		case "lab":
			return 2L;
		case "service":
			return 3L;
		default:
			return -1L;
		}
	}
	
	public void get(HttpServletRequest httpServletRequest) {
		
		Cookie[] cookies = null;

		cookies = httpServletRequest.getCookies();
		if (cookies != null) {
			for (int i = 0; i < cookies.length; i++) {

				if (cookies[i].getName().equals("UserID"))
					userId = Long.valueOf(cookies[i].getValue());

				if (cookies[i].getName().equals("SessionID"))
					sessionId = cookies[i].getValue();

			}
		} 
	}

	public String getSessionId() {
		return sessionId;
	}

	public Long getUserId() {
		return userId;
	}
	
}
