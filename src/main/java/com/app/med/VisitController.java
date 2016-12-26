package com.app.med;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.app.dao.IVisitTypeFinder;
import com.app.model.VisitType;
import com.app.repository.factory.Registry;

@Controller
public class VisitController {

	private static final Logger logger = LoggerFactory.getLogger(VisitController.class);
	
	private IVisitTypeFinder visitTypeFinder = Registry.visitTypeFinder();
	
	@RequestMapping(value = "/visit", method = RequestMethod.GET)
	public String registrationUser(Model model) {

		VisitType visit = new VisitType();

		model.addAttribute("visit", visit);

		List<VisitType> visitsList = visitTypeFinder.getAll();
		
		model.addAttribute("visitList", visitsList);

		return "freevisit";
	}

}
