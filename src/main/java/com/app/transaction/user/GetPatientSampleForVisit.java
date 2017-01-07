package com.app.transaction.user;

import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.app.model.lab.Lab;
import com.app.model.lab.Sample;
import com.app.registry.Registry;
import com.app.transaction.TransactionScript;

public class GetPatientSampleForVisit extends TransactionScript {

private static final Logger logger = LoggerFactory.getLogger(GetPatientSampleForVisit.class);
	
	public GetPatientSampleForVisit(HttpServletRequest request, HttpServletResponse response) {
		super(request, response);
	}
	
	@Override
	public void run() throws Exception {
		
		Lab lab = new Lab();
		
		getRequest().setAttribute("lab", lab);
		
		Long id = (Long) getRequest().getAttribute("userId");
		
		List<Lab> labList = Registry.labFinder().findByUserId(id);
		
		labList = labList.stream().filter(e -> e.isAdded()).collect(Collectors.toList());
		
		getRequest().setAttribute("labList", labList);
		
		Sample sample = new Sample();
		
		getRequest().setAttribute("sample", sample);
		
	}

}
