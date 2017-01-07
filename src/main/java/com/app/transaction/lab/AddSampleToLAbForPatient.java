package com.app.transaction.lab;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.app.model.lab.Lab;
import com.app.model.lab.Sample;
import com.app.path.PathVariable;
import com.app.registry.Registry;
import com.app.transaction.TransactionScript;

public class AddSampleToLAbForPatient extends TransactionScript {

	private static final Logger logger = LoggerFactory.getLogger(AddSampleToLAbForPatient.class);

	public AddSampleToLAbForPatient(HttpServletRequest request, HttpServletResponse response) {
		super(request, response);
	}
	
	@Override
	public void run() throws Exception {
		
		Long labId = PathVariable.getIdFromUrl(getRequest().getRequestURL().toString());
		
		Lab lab = Registry.labFinder().findById(labId);
		
		String name = getRequest().getParameter("name");
		String result = getRequest().getParameter("result");
		String standardPositive = getRequest().getParameter("standardP");
		String standardNegative = getRequest().getParameter("standardN");
		String unit = getRequest().getParameter("unit");
		String description = getRequest().getParameter("description");
		
		Sample sample = new Sample(name, result, standardPositive, standardNegative, unit, description);
		
		logger.info(sample.toString());
		
		lab.insertSample(sample);
		
		getRequest().setAttribute("id", labId);
		
	}

}
