package com.app.transaction.doktor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.runner.Request;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.app.model.visit.Visit;
import com.app.path.PathVariable;
import com.app.registry.Registry;
import com.app.transaction.TransactionScript;

public class GetNoteForThePatient extends TransactionScript {

	private static final Logger logger = LoggerFactory.getLogger(GetNoteForThePatient.class);

	public GetNoteForThePatient(HttpServletRequest request, HttpServletResponse response) {
		super(request, response);
	}

	@Override
	public void run() throws Exception {

		Long visitId = PathVariable.getIdFromUrl(getRequest().getRequestURL().toString());

		Visit visit = Registry.visitFinder().findById(visitId);
		
		logger.info("NOTE " + visit.getNote() != null ? visit.getNote() : "");

		if (visit.getNote() != null)
			getRequest().setAttribute("note", visit.getNote());
		else 
			getRequest().setAttribute("note", "");
		
		getRequest().setAttribute("id", visit.getPatient().getId());

	}

}
