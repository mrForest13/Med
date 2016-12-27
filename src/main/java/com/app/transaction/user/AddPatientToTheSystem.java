package com.app.transaction.user;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;


import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.app.model.user.Patient;
import com.app.transaction.TransactionScript;

public class AddPatientToTheSystem implements TransactionScript {

	private static final Logger logger = LoggerFactory.getLogger(AddPatientToTheSystem.class);

	private HttpServletRequest request;

	public AddPatientToTheSystem(HttpServletRequest request) {
		this.request = request;
	}

	@Override
	public void run() throws Exception {

		int userType = 0;
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String login = request.getParameter("login");
		String password = request.getParameter("password");
		String pesel = request.getParameter("pesel");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");

		Date birthDay = Date.valueOf(request.getParameter("birthDate"));
		
		char gender = request.getParameter("gender").charAt(0);
		
		Patient patient = new Patient(null, userType, firstName, lastName, login, password, null, pesel, email, phone,
				birthDay, gender);
		

		System.out.println(patient);
		
		patient.insert();

	}

}
