package com.app.transaction.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.app.model.visit.Visit;
import com.app.path.PathVariable;
import com.app.registry.Registry;
import com.app.transaction.TransactionScript;

public class CancelAppointmentForThePatient extends TransactionScript {

	private static final Logger logger = LoggerFactory.getLogger(CancelAppointmentForThePatient.class);
	
	public CancelAppointmentForThePatient(HttpServletRequest request, HttpServletResponse response) {
		super(request, response);
	}
	
	@Override
	public void run() throws Exception {
		
		Long id = PathVariable.getIdFromUrl(getRequest().getRequestURL().toString());
		
		Visit visit  = Registry.visitFinder().findById(id);
		
		visit.setPatient(null);
		
		logger.info(visit.toString());
		
		visit.update();
		
	}

}
