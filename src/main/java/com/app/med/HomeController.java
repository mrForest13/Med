package com.app.med;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.app.dao.IFinder;
import com.app.model.session.Session;
import com.app.model.visit.Visit;
import com.app.model.visit.VisitType;
import com.app.registry.Registry;
import com.app.transaction.TransactionScript;
import com.app.transaction.session.CheckUserPermissionAndCreateSession;
import com.app.transaction.user.AddPatientToTheSystem;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home() {

		return "redirect:/login";
	}

	@RequestMapping(value = "/registration", method = RequestMethod.GET)
	public String showrRegistrationUser() {

		return "registration";
	}

	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	public String registrationUser(HttpServletRequest request, HttpServletResponse response) {

		TransactionScript transactionScript = new AddPatientToTheSystem(request, response);

		try {
			transactionScript.run();
		} catch (Exception e) {
			e.printStackTrace();
			return "redirect:/registration";
		}

		return "redirect:/login";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String showLogin() {

		return "login";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String showLogin(HttpServletRequest request, HttpServletResponse response) {

		TransactionScript transactionScript = new CheckUserPermissionAndCreateSession(request, response);

		try {
			transactionScript.run();
		} catch (Exception e) {
			e.printStackTrace();
			return "redirect:/login";
		}

		return "redirect:/result";
	}
	
	@RequestMapping(value = "/noacces", method = RequestMethod.GET)
	public String noAcces(HttpServletRequest request, HttpServletResponse response) {
		
		return "noacces";
	}
	
	@RequestMapping(value = "/alreadylogged", method = RequestMethod.GET)
	public String alreadyLogged(HttpServletRequest request, HttpServletResponse response) {
		
		return "alreadylogged";
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logOut(HttpServletRequest request, HttpServletResponse response) {
		
		Long userId = (Long) request.getAttribute("userId");
		
		Session session = null;
		
		if(userId!= null) session = Registry.sessionFinder().findById(userId);
		
		if(session!=null) {
			session.setSessionIsActive(false);
			session.update();
		}
		
		return "redirect:/login";
	}
	
	@RequestMapping(value = "/result", method = RequestMethod.GET)
	public String getresult(HttpServletRequest request, HttpServletResponse response) {
		
		return "result";
	}

}
