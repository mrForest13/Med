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
import com.app.path.PathVariable;
import com.app.registry.Registry;
import com.app.transaction.TransactionScript;
import com.app.transaction.lab.AddSampleToLAbForPatient;
import com.app.transaction.lab.ConfirmLabForPatient;
import com.app.transaction.lab.GetLabVisitForPatient;
import com.app.transaction.lab.ShowAddSampleForm;

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
	public String addSampleForm(HttpServletRequest request, HttpServletResponse response) throws Exception {

		TransactionScript transactionScript = new ShowAddSampleForm(request, response);

		transactionScript.run();
		
		return "addsample";
	}
	

	@RequestMapping(value = "/patient/{id}", method = RequestMethod.POST)
	public String addSample(HttpServletRequest request, HttpServletResponse response) throws Exception {

		Long labId = PathVariable.getIdFromUrl(request.getRequestURL().toString());
		
		TransactionScript transactionScript = new AddSampleToLAbForPatient(request, response);

		transactionScript.run();
		
		return "redirect:/lab/patient/"+labId;
	}
	
	@RequestMapping(value = "/patient/confirm/{id}", method = RequestMethod.GET)
	public String confirmLab(HttpServletRequest request, HttpServletResponse response) throws Exception {

		TransactionScript transactionScript = new ConfirmLabForPatient(request, response);

		transactionScript.run();

		return "redirect:/lab/search";
	}

}
