package com.app.transaction.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.app.model.medical.Referal;
import com.app.model.medical.finder.ReferalFinder;
import com.app.model.user.Patient;
import com.app.model.user.finder.PatientFinder;
import com.app.model.visit.Visit;
import com.app.path.PathVariable;
import com.app.query.Criteria;
import com.app.query.QueryObject;
import com.app.registry.Registry;
import com.app.transaction.TransactionScript;
import com.app.transaction.payment.Money;
import com.app.transaction.payment.RollbackCash;

public class CancelAppointmentForThePatient extends TransactionScript {

	private static final Logger logger = LoggerFactory.getLogger(CancelAppointmentForThePatient.class);
	private RedirectAttributes redirectAttributes;
	
	public CancelAppointmentForThePatient(HttpServletRequest request, HttpServletResponse response) {
		super(request, response);
	}
	
	public CancelAppointmentForThePatient(HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		super(request, response);
		this.redirectAttributes = redirectAttributes;
	}
	
	@Override
	public void run() throws Exception {
		
		Long id = PathVariable.getIdFromUrl(getRequest().getRequestURL().toString());
		
		Visit visit  = Registry.visitFinder().findById(id);
		
		visit.setPatient(null);
		
		Patient user = ((PatientFinder) Registry.patientFinder()).findByPesel(getRequest().getParameter("pesel"));
		
		Long userId = user.getId();
		
		QueryObject queryReferal = new QueryObject(ReferalFinder.TABLENAME);
		queryReferal.addCriteria(Criteria.equalsLong("REFERAL_USER_PACJENT_ID", userId));
		queryReferal.addCriteria(Criteria.equalsString("REFERAL_IS_USED", "Y"));
		queryReferal.addCriteria(Criteria.equalsLong("REFERAL_VISIT_TYPE_ID", visit.getVisitType().getId()));

		List<Referal> referalList = Registry.referalFinder().findByQueryObject(queryReferal);
		
		
		if(!referalList.isEmpty()){
			Referal referal = referalList.get(0);
			referal.setUsed(false);
			referal.update();
		}
		
		logger.info(visit.toString());
		
		visit.setVisitPrice(new Money(RollbackCash.get(user.getPesel()), "PLN"));
		visit.update();
		
		redirectAttributes.addAttribute("pesel", getRequest().getParameter("pesel"));
		
	}

}
