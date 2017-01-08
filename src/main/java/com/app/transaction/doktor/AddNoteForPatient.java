package com.app.transaction.doktor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.app.model.visit.Visit;
import com.app.path.PathVariable;
import com.app.registry.Registry;
import com.app.transaction.TransactionScript;

public class AddNoteForPatient extends TransactionScript {

	private static final Logger logger = LoggerFactory.getLogger(AddNoteForPatient.class);

	public AddNoteForPatient(HttpServletRequest request, HttpServletResponse response) {
		super(request, response);
	}

	@Override
	public void run() throws Exception {
		
		Long visitId = PathVariable.getIdFromUrl(getRequest().getRequestURL().toString());

		Visit visit = Registry.visitFinder().findById(visitId);
		
		logger.info(visit.toString());
		
		String note = getRequest().getParameter("note");
		
		if(visit.getNote()==null || !visit.getNote().equals(note)) {
			visit.setNote(note);
			visit.update();
		}
		
		getRequest().setAttribute("id", visit.getPatient().getId());
		
	}
	
}
