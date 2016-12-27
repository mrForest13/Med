package com.app.med;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.app.transaction.TransactionScript;
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
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}
	
	@RequestMapping(value = "/registration", method = RequestMethod.GET)
	public String showrRegistrationUser() {

		return "user/registration";
	}

	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	public String registrationUser(HttpServletRequest request) {
		
		TransactionScript transactionScript = new AddPatientToTheSystem(request);
		
		try {
			transactionScript.run();
		} catch (Exception e) {
			e.printStackTrace();
			return "redirect:/user/registration";
		}
		
		return "redirect:/login";
	}
	
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String showLogin() {

		return "user/login";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String registrationUser(@RequestParam("login") String username, @RequestParam("password") String password, HttpServletResponse response) {

		response.addCookie(new Cookie("SessionID", "123"));
		
		return "userpage";
	}
	
	@RequestMapping(value = "/result", method = RequestMethod.GET)
	public String result() {
		
		return "result";
	}

	
}
