package com.app.transaction.lab;

import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.app.model.lab.Lab;
import com.app.model.user.Patient;
import com.app.model.user.finder.PatientFinder;
import com.app.registry.Registry;
import com.app.transaction.TransactionScript;

public class GetLabVisitForPatient extends TransactionScript {

	private static final Logger logger = LoggerFactory.getLogger(GetLabVisitForPatient.class);

	public GetLabVisitForPatient(HttpServletRequest request, HttpServletResponse response) {
		super(request, response);
	}

	@Override
	public void run() throws Exception {

		Lab lab = new Lab();

		getRequest().setAttribute("lab", lab);

		Patient user = ((PatientFinder) Registry.patientFinder()).findByPesel(getRequest().getParameter("pesel"));

		if (user != null) {

			logger.info("User id " + user.getId());

			List<Lab> labList = Registry.labFinder().findByUserId(user.getId());
			labList = labList.stream().filter(e -> !e.isAdded()).collect(Collectors.toList());
			
			getRequest().setAttribute("labList", labList);
		}

	}

}
