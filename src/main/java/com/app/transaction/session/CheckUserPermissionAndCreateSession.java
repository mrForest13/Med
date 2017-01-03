package com.app.transaction.session;

import java.sql.Timestamp;
import java.util.Calendar;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.app.model.session.Session;
import com.app.model.user.User;
import com.app.model.visit.finder.VisitFinder;
import com.app.registry.Registry;
import com.app.transaction.TransactionScript;

public class CheckUserPermissionAndCreateSession extends TransactionScript {

	private static final Logger logger = LoggerFactory.getLogger(CheckUserPermissionAndCreateSession.class);
	
	public CheckUserPermissionAndCreateSession(HttpServletRequest request, HttpServletResponse response) {
		super(request, response);
	}

	@Override
	public void run() throws Exception {

		String login = getRequest().getParameter("login");
		String password = getRequest().getParameter("password");

		User user = Registry.userFinder().findByLogin(login);
		
		logger.info(user.toString());

		if (user == null || !user.getPassword().equals(password))
			throw new Exception();
		else {
			String sessionId = SessionIdentifierGenerator.nextSessionId();
			
			logger.info("Session Id: " + sessionId);
			
			Timestamp startSession = new Timestamp(Calendar.getInstance().getTime().getTime());
			
			Session newSession = Registry.sessionFinder().findById(user.getId());
			
			if(newSession==null) {
				newSession = new Session(null, user, sessionId, startSession, true);
				newSession.insert();
			} else {
				newSession.setSessionDate(startSession);
				newSession.setSessionIsActive(true);
				newSession.setSessionHash(sessionId);
				newSession.update();
			}
			
			getResponse().addCookie(new Cookie("SessionID", sessionId));
			getResponse().addCookie(new Cookie("UserID", user.getId().toString()));
		}
	}

}
