package com.app.transaction.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.app.model.user.Patient;
import com.app.registry.Registry;
import com.app.transaction.TransactionScript;

public class UpdateUserData extends TransactionScript {
	
	private static final Logger logger = LoggerFactory.getLogger(UpdateUserData.class);

	public UpdateUserData(HttpServletRequest request, HttpServletResponse response) {
		super(request, response);
	}
	
	@Override
	public void run() throws Exception {
		
		Long id = (Long) getRequest().getAttribute("userId");
		String email = getRequest().getParameter("email");
		String phone = getRequest().getParameter("phone");
		String login = getRequest().getParameter("login");
		String oldPassword = getRequest().getParameter("oldpassword");
		String newPassword = getRequest().getParameter("newpassword");
		
		Patient user = Registry.patientFinder().find(id);
		
		logger.info(user.toString());
		
		if(!user.getPassword().equals(oldPassword)) throw new Exception();
		
		user.setEmail(email);
		user.setPhone(phone);
		user.setLogin(login);
		user.setPassword(newPassword);
		
		logger.info(user.toString());
		
		user.update();
		
	}

}
