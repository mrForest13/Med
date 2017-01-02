package com.app.med;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.app.model.medical.MedicalPrescription;
import com.app.model.medical.finder.MedicalPrescriptionFinder;
import com.app.registry.Registry;
import com.app.transaction.TransactionScript;
import com.app.transaction.user.BookAppointmentForThePatient;
import com.app.transaction.user.CancelAppointmentForThePatient;
import com.app.transaction.user.GetFreeVisitForPatient;
import com.app.transaction.user.GetUserData;
import com.app.transaction.user.GetVisitForPatient;
import com.app.transaction.user.UpdateUserData;

@Controller
@RequestMapping("/user")
public class UserController {

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	


	@RequestMapping(value = "/visit", method = RequestMethod.GET)
	public String registrationUser(HttpServletRequest request, HttpServletResponse response) {

		TransactionScript transactionScript = new GetFreeVisitForPatient(request, response);

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

		TransactionScript transactionScript = new GetUserData(request, response);

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
	
	
	@RequestMapping(value = "/myvisit", method = RequestMethod.GET)
	public String getMyVisit(HttpServletRequest request, HttpServletResponse response) {

		TransactionScript transactionScript = new GetVisitForPatient(request, response);

		try {
			transactionScript.run();
		} catch (Exception e) {
			e.printStackTrace();
			return "redirect:/blad";//TO DO
		}
		
		return "myvisit";
	}
	
	@RequestMapping(value = "/cancel/{id}", method = RequestMethod.GET)
	public String cancelAppointment(HttpServletRequest request, HttpServletResponse response) {

		TransactionScript transactionScript = new CancelAppointmentForThePatient(request, response);

		try {
			transactionScript.run();
		} catch (Exception e) {
			e.printStackTrace();
			return "redirect:/blad";//TO DO
		}
		
		return "redirect:/user/myvisit";
	}
	
	@RequestMapping(value = "/book/{id}", method = RequestMethod.GET)
	public String bookAppointment(HttpServletRequest request, HttpServletResponse response) {

		TransactionScript transactionScript = new BookAppointmentForThePatient(request, response);

		try {
			transactionScript.run();
		} catch (Exception e) {
			e.printStackTrace();
			return "redirect:/blad";//TO DO
		}
		
		return "redirect:/user/myvisit";
	}
	
	@RequestMapping(value = "/prescriptions", method = RequestMethod.GET)
	public String getMedicalPrescription(HttpServletRequest request, HttpServletResponse response) {
		
		Long id = (Long) request.getAttribute("userId");
		
		MedicalPrescriptionFinder prescription = new MedicalPrescriptionFinder();

		request.setAttribute("prescription", prescription);
		
		List<MedicalPrescription> prescriptionList = Registry.medicalPrescriptionFinder().findByUserId(id);
		
		request.setAttribute("prescriptionList", prescriptionList);
		
		return "prescriptions";
	}
}
