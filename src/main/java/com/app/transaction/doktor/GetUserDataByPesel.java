package com.app.transaction.doktor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.app.model.user.Patient;
import com.app.model.user.finder.PatientFinder;
import com.app.registry.Registry;
import com.app.transaction.TransactionScript;

public class GetUserDataByPesel extends TransactionScript {

	private static final Logger logger = LoggerFactory.getLogger(GetUserDataByPesel.class);

	public GetUserDataByPesel(HttpServletRequest request, HttpServletResponse response) {
		super(request, response);
	}

	@Override
	public void run() throws Exception {

		Patient user = ((PatientFinder) Registry.patientFinder()).findByPesel(getRequest().getParameter("pesel"));

		if (user != null) {

			Patient patient = Registry.patientFinder().findById(user.getId());

			getRequest().setAttribute("user", patient);

		}

	}

}
