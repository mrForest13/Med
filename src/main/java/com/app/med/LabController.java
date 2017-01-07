package com.app.med;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.app.model.lab.Lab;
import com.app.model.lab.Sample;
import com.app.model.user.Patient;
import com.app.model.user.finder.PatientFinder;
import com.app.model.visit.Visit;
import com.app.model.visit.VisitType;
import com.app.path.PathVariable;
import com.app.registry.Registry;
import com.app.transaction.TransactionScript;
import com.app.transaction.lab.GetLabVisitForPatient;
import com.app.transaction.user.GetUserData;

@Controller
@RequestMapping("/lab")
public class LabController {

	private static final Logger logger = LoggerFactory.getLogger(LabController.class);

	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public String showPatient(HttpServletRequest request, HttpServletResponse response) {

		return "labshowvisit";
	}

	@RequestMapping(value = "/patient", method = RequestMethod.POST)
	public String getPatient(HttpServletRequest request, HttpServletResponse response) throws Exception {

		TransactionScript transactionScript = new GetLabVisitForPatient(request, response);

		transactionScript.run();

		return "labshowvisit";
	}

	@RequestMapping(value = "/patient/{id}", method = RequestMethod.GET)
	public String addSample(HttpServletRequest request, HttpServletResponse response) {

		Long labId = PathVariable.getIdFromUrl(request.getRequestURL().toString());
		
		Lab lab = Registry.labFinder().findById(labId);
		
		Sample sample = new Sample();
		
		request.setAttribute("sample", sample);
		
		List<Sample> sampleList = lab.getSample();
		
		request.setAttribute("sampleList", sampleList);
		
		return "addsample";
	}

}
