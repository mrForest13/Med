package com.app.med;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.app.dao.IFinder;
import com.app.model.visit.Visit;
import com.app.model.visit.VisitType;
import com.app.model.visit.finder.VisitFinder;
import com.app.registry.Registry;

@Controller
public class VisitController {

	private static final Logger logger = LoggerFactory.getLogger(VisitController.class);
	
	private IFinder<Visit> visitFinder = Registry.visitFinder();
	
	@RequestMapping(value = "/visit", method = RequestMethod.GET)
	public String registrationUser(Model model) {

		VisitType visit = new VisitType();

		model.addAttribute("visit", visit);

		List<Visit> visitsList = visitFinder.getAll();
		
		model.addAttribute("visitList", visitsList);

		return "freevisit";
	}

}
