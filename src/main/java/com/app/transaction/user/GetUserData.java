package com.app.transaction.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.app.model.user.Patient;
import com.app.registry.Registry;
import com.app.transaction.TransactionScript;

public class GetUserData extends TransactionScript {

	public GetUserData(HttpServletRequest request, HttpServletResponse response) {
		super(request, response);
	}
	
	@Override
	public void run() throws Exception {
		
		Long id = (Long) getRequest().getAttribute("userId");
		
		Patient patient = Registry.patientFinder().findById(id);
		
		getRequest().setAttribute("user", patient);
		
	}

}
