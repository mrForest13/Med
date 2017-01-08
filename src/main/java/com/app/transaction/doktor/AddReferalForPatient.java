package com.app.transaction.doktor;

import java.sql.Date;
import java.util.Calendar;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.app.model.medical.Referal;
import com.app.model.user.Doctor;
import com.app.model.user.Patient;
import com.app.model.visit.VisitType;
import com.app.path.PathVariable;
import com.app.registry.Registry;
import com.app.transaction.TransactionScript;

public class AddReferalForPatient extends TransactionScript {

	private static final Logger logger = LoggerFactory.getLogger(AddReferalForPatient.class);

	public AddReferalForPatient(HttpServletRequest request, HttpServletResponse response) {
		super(request, response);
	}

	@Override
	public void run() throws Exception {

		Long id = (Long) getRequest().getAttribute("userId");
		String visitTypeS = getRequest().getParameter("visitType");
		
		Long userId = PathVariable.getSecendIdFromUrl(getRequest().getRequestURL().toString());

		VisitType visitType = Registry.visitTypeFinder().getAll().stream()
				.filter(e -> e.getVisitType().equals(visitTypeS)).collect(Collectors.toList()).get(0);

		Doctor doctor = Registry.doctorFinder().findById(id);
		Patient patient = Registry.patientFinder().findById(userId);
		Date dateOfissue = new Date(Calendar.getInstance().getTime().getTime());

		Referal referal = new Referal(null, patient, doctor, visitType, false, dateOfissue);
		referal.insert();
		
		getRequest().setAttribute("pesel", patient.getPesel());
	}

}