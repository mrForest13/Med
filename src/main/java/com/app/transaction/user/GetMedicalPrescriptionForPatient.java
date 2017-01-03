package com.app.transaction.user;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.app.model.medical.MedicalPrescription;
import com.app.model.medical.Medicament;
import com.app.model.medical.finder.MedicalPrescriptionFinder;
import com.app.registry.Registry;
import com.app.transaction.TransactionScript;

public class GetMedicalPrescriptionForPatient extends TransactionScript {

	private static final Logger logger = LoggerFactory.getLogger(GetMedicalPrescriptionForPatient.class);
	
	public GetMedicalPrescriptionForPatient(HttpServletRequest request, HttpServletResponse response) {
		super(request, response);
	}
	
	
	@Override
	public void run() throws Exception {
		
		Long id = (Long) getRequest().getAttribute("userId");

		MedicalPrescriptionFinder prescription = new MedicalPrescriptionFinder();

		getRequest().setAttribute("prescription", prescription);

		List<MedicalPrescription> prescriptionList = Registry.medicalPrescriptionFinder().findByUserId(id);

		getRequest().setAttribute("prescriptionList", prescriptionList);

		Medicament medicament = new Medicament();

		getRequest().setAttribute("medicament", medicament);
		
	}

}
