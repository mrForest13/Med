package com.app.med;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.app.transaction.TransactionScript;
import com.app.transaction.user.BookAppointmentForThePatient;
import com.app.transaction.user.CancelAppointmentForThePatient;
import com.app.transaction.user.GetFreeVisitForPatient;
import com.app.transaction.user.GetMedicalPrescriptionForPatient;
import com.app.transaction.user.GetPatientSampleForVisit;
import com.app.transaction.user.GetReferalForPatient;
import com.app.transaction.user.GetSearchOptionForVisit;
import com.app.transaction.user.GetUserData;
import com.app.transaction.user.GetVisitForPatient;
import com.app.transaction.user.UpdateUserData;

@Controller
@RequestMapping("/user")
public class UserController {

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@RequestMapping(value = "/visit", method = { RequestMethod.POST, RequestMethod.GET })
	public String registrationUser(HttpServletRequest request, HttpServletResponse response) throws Exception {

		TransactionScript transactionScript1 = new GetFreeVisitForPatient(request, response);
		TransactionScript transactionScript2 = new GetSearchOptionForVisit(request, response);

		transactionScript1.run();
		transactionScript2.run();

		return "freevisit";
	}

	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public String searchVisit(HttpServletRequest request, HttpServletResponse response) throws Exception {

		TransactionScript transactionScript = new GetSearchOptionForVisit(request, response);

		transactionScript.run();

		return "freevisit";
	}

	@RequestMapping(value = "/mypage", method = RequestMethod.GET)
	public String userProfil(HttpServletRequest request, HttpServletResponse response) throws Exception {

		TransactionScript transactionScript = new GetUserData(request, response);

		transactionScript.run();

		return "userpage";
	}

	@RequestMapping(value = "/mypage", method = RequestMethod.POST)
	public String userProfilUpdate(HttpServletRequest request, HttpServletResponse response) throws Exception {

		TransactionScript transactionScript = new UpdateUserData(request, response);

		transactionScript.run();

		return "redirect:/user/mypage";
	}

	@RequestMapping(value = "/myvisit", method = RequestMethod.GET)
	public String getMyVisit(HttpServletRequest request, HttpServletResponse response) throws Exception {

		TransactionScript transactionScript = new GetVisitForPatient(request, response);

		transactionScript.run();

		return "myvisit";
	}

	@RequestMapping(value = "/cancel/{id}", method = RequestMethod.GET)
	public String cancelAppointment(HttpServletRequest request, HttpServletResponse response) throws Exception {

		TransactionScript transactionScript = new CancelAppointmentForThePatient(request, response);

		transactionScript.run();

		return "redirect:/user/myvisit";
	}

	@RequestMapping(value = "/book/{id}", method = RequestMethod.GET)
	public String bookAppointment(HttpServletRequest request, HttpServletResponse response) throws Exception {

		TransactionScript transactionScript = new BookAppointmentForThePatient(request, response);

		transactionScript.run();

		return "redirect:/user/myvisit";
	}

	@RequestMapping(value = "/prescriptions", method = RequestMethod.GET)
	public String getMedicalPrescription(HttpServletRequest request, HttpServletResponse response) throws Exception {

		TransactionScript transactionScript = new GetMedicalPrescriptionForPatient(request, response);

		transactionScript.run();

		return "prescriptions";
	}

	@RequestMapping(value = "/referal", method = RequestMethod.GET)
	public String getReferals(HttpServletRequest request, HttpServletResponse response) throws Exception {

		TransactionScript transactionScript = new GetReferalForPatient(request, response);

		transactionScript.run();

		return "showreferal";
	}
	
	@RequestMapping(value = "/examination", method = RequestMethod.GET)
	public String showSample(HttpServletRequest request, HttpServletResponse response) throws Exception {

		TransactionScript transactionScript = new GetPatientSampleForVisit(request, response);

		transactionScript.run();
		
		return "examination";
	}

}
