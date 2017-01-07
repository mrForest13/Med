package com.app.transaction.lab;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.app.model.lab.Lab;
import com.app.path.PathVariable;
import com.app.registry.Registry;
import com.app.transaction.TransactionScript;

public class ConfirmLabForPatient extends TransactionScript {

	private static final Logger logger = LoggerFactory.getLogger(ConfirmLabForPatient.class);

	public ConfirmLabForPatient(HttpServletRequest request, HttpServletResponse response) {
		super(request, response);
	}

	@Override
	public void run() throws Exception {

		Long labId = PathVariable.getIdFromUrl(getRequest().getRequestURL().toString());

		Lab lab = Registry.labFinder().findById(labId);

		lab.setAdded(true);
		lab.update();
	}

}
