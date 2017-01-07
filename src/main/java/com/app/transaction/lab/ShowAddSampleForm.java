package com.app.transaction.lab;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.app.model.lab.Lab;
import com.app.model.lab.Sample;
import com.app.path.PathVariable;
import com.app.registry.Registry;
import com.app.transaction.TransactionScript;

public class ShowAddSampleForm extends TransactionScript {

	private static final Logger logger = LoggerFactory.getLogger(ShowAddSampleForm.class);

	public ShowAddSampleForm(HttpServletRequest request, HttpServletResponse response) {
		super(request, response);
	}

	@Override
	public void run() throws Exception {

		Long labId = PathVariable.getIdFromUrl(getRequest().getRequestURL().toString());

		Lab lab = Registry.labFinder().findById(labId);

		Sample sample = new Sample();

		getRequest().setAttribute("sample", sample);

		List<Sample> sampleList = lab.getSample();

		getRequest().setAttribute("sampleList", sampleList);

		getRequest().setAttribute("id", labId);

	}

}
