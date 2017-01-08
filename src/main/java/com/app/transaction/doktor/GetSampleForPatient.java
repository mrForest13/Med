package com.app.transaction.doktor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.app.model.lab.Lab;
import com.app.model.lab.finder.LabFinder;
import com.app.path.PathVariable;
import com.app.registry.Registry;
import com.app.transaction.TransactionScript;

public class GetSampleForPatient extends TransactionScript {

	private static final Logger logger = LoggerFactory.getLogger(GetSampleForPatient.class);

	public GetSampleForPatient(HttpServletRequest request, HttpServletResponse response) {
		super(request, response);
	}

	@Override
	public void run() throws Exception {
		
		Long visitId = PathVariable.getIdFromUrl(getRequest().getRequestURL().toString());
		
		Lab lab = ((LabFinder)Registry.labFinder()).findByVisitId(visitId);
		
		getRequest().setAttribute("labList", lab.getSample());

	}

}
