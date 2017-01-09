package com.app.med;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.app.transaction.TransactionScript;
import com.app.transaction.doktor.AddNoteForPatient;
import com.app.transaction.doktor.AddPrescriptionForPatient;
import com.app.transaction.doktor.AddReferalForPatient;
import com.app.transaction.doktor.GetNoteForThePatient;
import com.app.transaction.doktor.GetPrescriptionForPatient;
import com.app.transaction.doktor.GetSampleForPatient;
import com.app.transaction.doktor.GetUserDataByPesel;
import com.app.transaction.doktor.GetVisitForPatient;
import com.app.transaction.doktor.ShowReferalForPatient;

@Controller
@RequestMapping("/doktor")
public class DoktorController {

	private static final Logger logger = LoggerFactory.getLogger(DoktorController.class);

	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public String showPatient(HttpServletRequest request, HttpServletResponse response) {

		return "doktorpatientsearch";
	}

	@RequestMapping(value = "/patient", method = { RequestMethod.POST, RequestMethod.GET })
	public String getPatient(HttpServletRequest request, HttpServletResponse response) throws Exception {

		TransactionScript transactionScript = new GetUserDataByPesel(request, response);

		transactionScript.run();

		return "doktorpatientsearch";
	}

	@RequestMapping(value = "/patient/{id}/referal", method = RequestMethod.GET)
	public String showPatientReferal(HttpServletRequest request, HttpServletResponse response) throws Exception {

		TransactionScript transactionScript = new ShowReferalForPatient(request, response);

		transactionScript.run();

		return "doktorshowreferal";
	}

	@RequestMapping(value = "/patient/{id}/referal", method = RequestMethod.POST)
	public String addPatientReferal(HttpServletRequest request, HttpServletResponse response) throws Exception {

		TransactionScript transactionScript = new AddReferalForPatient(request, response);

		transactionScript.run();

		return "redirect:/doktor/patient/{id}/referal";
	}

	@RequestMapping(value = "/patient/{id}/visit", method = RequestMethod.GET)
	public String getVisit(HttpServletRequest request, HttpServletResponse response) throws Exception {

		TransactionScript transactionScript = new GetVisitForPatient(request, response);

		transactionScript.run();

		return "patientvisit";
	}

	@RequestMapping(value = "/patient/{id}/examination/{id}", method = RequestMethod.GET)
	public String showSample(HttpServletRequest request, HttpServletResponse response) throws Exception {

		TransactionScript transactionScript = new GetSampleForPatient(request, response);

		transactionScript.run();

		return "doktorexamination";
	}

	@RequestMapping(value = "/patient/{userid}/note/{visitid}", method = RequestMethod.GET)
	public String showNote(HttpServletRequest request, HttpServletResponse response) throws Exception {

		TransactionScript transactionScript = new GetNoteForThePatient(request, response);

		transactionScript.run();

		return "doktornote";
	}

	@RequestMapping(value = "/patient/{userid}/note/{visitid}", method = RequestMethod.POST)
	public String addNote(HttpServletRequest request, HttpServletResponse response) throws Exception {

		TransactionScript transactionScript = new AddNoteForPatient(request, response);

		transactionScript.run();

		return "redirect:/doktor/patient/{userid}/note/{visitid}";
	}

	@RequestMapping(value = "/patient/{userid}/prescriptions", method = RequestMethod.GET)
	public String showPrescriptions(HttpServletRequest request, HttpServletResponse response,
			RedirectAttributes redirectAttributes) throws Exception {

		TransactionScript transactionScript = new GetPrescriptionForPatient(request, response);

		transactionScript.run();

		return "doktorprescriptions";
	}

	@RequestMapping(value = "/patient/{userid}/prescriptions", method = RequestMethod.POST)
	public String addPrescriptions(HttpServletRequest request, HttpServletResponse response,
			RedirectAttributes redirectAttributes) throws Exception {

		TransactionScript transactionScript = new AddPrescriptionForPatient(request, response, redirectAttributes);

		transactionScript.run();

		return "redirect:/doktor/patient/{userid}/prescriptions";
	}

	@RequestMapping(value = "/patient/{userid}/prescriptions", method = RequestMethod.GET)
	public String showPrescriptions(HttpServletRequest request, HttpServletResponse response) throws Exception {

		TransactionScript transactionScript = new GetPrescriptionForPatient(request, response);

		transactionScript.run();

		return "doktorprescriptions";
	}

}
