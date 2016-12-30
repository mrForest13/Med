package com.app.transaction.user;

import java.sql.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.app.model.user.Patient;
import com.app.transaction.TransactionScript;

public class AddPatientToTheSystem extends TransactionScript {

	public AddPatientToTheSystem(HttpServletRequest request, HttpServletResponse response) {
		super(request, response);
	}

	private static final Logger logger = LoggerFactory.getLogger(AddPatientToTheSystem.class);

	@Override
	public void run() throws Exception {

		int userType = 0;
		String firstName = getRequest().getParameter("firstName");
		String lastName = getRequest().getParameter("lastName");
		String login = getRequest().getParameter("login");
		String password = getRequest().getParameter("password");
		String pesel = getRequest().getParameter("pesel");
		String email = getRequest().getParameter("email");
		String phone = getRequest().getParameter("phone");

		Date birthDay = Date.valueOf(getRequest().getParameter("birthDate"));
		
		char gender = getRequest().getParameter("gender").charAt(0);
		
		Patient patient = new Patient(null, userType, firstName, lastName, login, password, null, pesel, email, phone,
				birthDay, gender);
		

		logger.info(patient.toString());
		
		patient.insert();

	}

}
