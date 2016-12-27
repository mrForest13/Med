package com.app.med;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.app.model.user.User;
import com.app.model.user.finder.Finder;
import com.app.model.user.finder.PatientFinder;

@Controller()
@RequestMapping("/user")
public class UserController {

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@RequestMapping(value = "/registration", method = RequestMethod.GET)
	public String showrRegistrationUser() {

		return "registration";
	}

	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	public String registrationUser() {

		PatientFinder finder = new PatientFinder();
		
		System.out.println(finder.find(3L).toString());
		
		return null;
	}

}
