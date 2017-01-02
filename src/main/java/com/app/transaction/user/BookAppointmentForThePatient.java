package com.app.transaction.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.app.model.user.User;
import com.app.model.visit.Visit;
import com.app.path.PathVariable;
import com.app.registry.Registry;
import com.app.transaction.TransactionScript;

public class BookAppointmentForThePatient extends TransactionScript{

	private static final Logger logger = LoggerFactory.getLogger(BookAppointmentForThePatient.class);
	
	public BookAppointmentForThePatient(HttpServletRequest request, HttpServletResponse response) {
		super(request, response);
	}
	
	@Override
	public void run() throws Exception {
		
		Long id = PathVariable.getIdFromUrl(getRequest().getRequestURL().toString());
		
		Visit visit  = Registry.visitFinder().findById(id);
		
		Long userId = (Long) getRequest().getAttribute("userId");
		
		User user = Registry.patientFinder().find(userId);
		
		visit.setPatient(user);
		
		logger.info(visit.toString());
		
		visit.update();
		
	}

}