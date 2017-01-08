package com.app.transaction.user;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.app.model.user.Doctor;
import com.app.model.user.finder.DoctorFinder;
import com.app.model.visit.VisitType;
import com.app.model.visit.finder.VisitTypeFinder;
import com.app.registry.Registry;
import com.app.transaction.TransactionScript;

public class GetSearchOptionForVisit extends TransactionScript {

	private static final Logger logger = LoggerFactory.getLogger(GetSearchOptionForVisit.class);

	public GetSearchOptionForVisit(HttpServletRequest request, HttpServletResponse response) {
		super(request, response);
	}

	@Override
	public void run() throws Exception {

		VisitType visit = new VisitType();

		getRequest().setAttribute("visitType", visit);

		List<VisitType> visitsTypeList = Registry.visitTypeFinder().getAll();

		getRequest().setAttribute("visitTypeList", visitsTypeList);

		Doctor doctor = new Doctor();

		getRequest().setAttribute("doctor", doctor);

		List<Doctor> doctorList = ((DoctorFinder) Registry.doctorFinder()).getAll();

		getRequest().setAttribute("doctorList", doctorList);
	}

}
