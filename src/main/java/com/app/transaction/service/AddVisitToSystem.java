package com.app.transaction.service;

import java.sql.Timestamp;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.app.model.lab.Lab;
import com.app.model.user.Doctor;
import com.app.model.visit.Visit;
import com.app.model.visit.VisitType;
import com.app.transaction.TransactionScript;
import com.app.transaction.payment.Money;

public class AddVisitToSystem extends TransactionScript {

	private static final Logger logger = LoggerFactory.getLogger(AddVisitToSystem.class);


	public AddVisitToSystem(HttpServletRequest request, HttpServletResponse response) {
		super(request, response);
	}

	@Override
	public void run() throws Exception {
		
		Long doctorId = Long.valueOf(getRequest().getParameter("doctor"));
		Doctor doctor = new Doctor();
		doctor.setId(doctorId);
		
		Long visitTypeId = Long.valueOf(getRequest().getParameter("visitType"));
		VisitType visitType = new VisitType();
		visitType.setId(visitTypeId);
		
		Double price = Double.valueOf(getRequest().getParameter("price"));
		
		String day = getRequest().getParameter("date");
		String start = getRequest().getParameter("start");
		String end = getRequest().getParameter("end");
		
		logger.info("DATE " + day +" " + start + ":00");
		
		Timestamp visitDateFrom = Timestamp.valueOf(day +" " + start + ":00");
		Timestamp visitDateTo = Timestamp.valueOf(day +" " + end + ":00");
		
		Visit visit = new Visit(null, null, doctor, visitType, visitDateFrom, visitDateTo, new Money(price,"PLN"), false, null);
		visit.insert();
		
		if(visitTypeId==6) {
			Lab lab = new Lab(null, visit, null, false);
			lab.insert();
		}
		
	}
	
	
}
