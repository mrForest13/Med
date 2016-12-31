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
import com.app.model.user.Patient;
import com.app.model.visit.Visit;
import com.app.model.visit.VisitType;
import com.app.registry.Registry;
import com.app.transaction.TransactionScript;
import com.app.transaction.user.AddPatientToTheSystem;
import com.app.transaction.user.ShowFreeVisitForPatient;
import com.app.transaction.user.ShowUserData;
import com.app.transaction.user.UpdateUserData;

@Controller
@RequestMapping("/user")
public class UserController {

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	


	@RequestMapping(value = "/visit", method = RequestMethod.GET)
	public String registrationUser(HttpServletRequest request, HttpServletResponse response) {

		TransactionScript transactionScript = new ShowFreeVisitForPatient(request, response);

		try {
			transactionScript.run();
		} catch (Exception e) {
			e.printStackTrace();
			return "redirect:/blad";//TO DO
		}

		return "freevisit";
	}
	
	@RequestMapping(value = "/mypage", method = RequestMethod.GET)
	public String userProfil(HttpServletRequest request, HttpServletResponse response) {

		TransactionScript transactionScript = new ShowUserData(request, response);

		try {
			transactionScript.run();
		} catch (Exception e) {
			e.printStackTrace();
			return "redirect:/blad";//TO DO
		}

		return "userpage";
	}
	
	@RequestMapping(value = "/mypage", method = RequestMethod.POST)
	public String userProfilUpdate(HttpServletRequest request, HttpServletResponse response) {

		TransactionScript transactionScript = new UpdateUserData(request, response);

		try {
			transactionScript.run();
		} catch (Exception e) {
			e.printStackTrace();
			return "redirect:/blad";//TO DO
		}

		return "redirect:/user/mypage";
	}
}
