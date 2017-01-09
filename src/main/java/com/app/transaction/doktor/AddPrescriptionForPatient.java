package com.app.transaction.doktor;

import java.sql.Timestamp;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.app.model.medical.MedicalPrescription;
import com.app.model.medical.Medicament;
import com.app.model.user.Doctor;
import com.app.model.user.Patient;
import com.app.path.PathVariable;
import com.app.registry.Registry;
import com.app.transaction.TransactionScript;

public class AddPrescriptionForPatient extends TransactionScript {

	private static final Logger logger = LoggerFactory.getLogger(AddPrescriptionForPatient.class);

	private RedirectAttributes redirectAttributes;
	
	public AddPrescriptionForPatient(HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		super(request, response);
		this.redirectAttributes = redirectAttributes;
	}

	@Override
	public void run() throws Exception {

		String prescriptionId = getRequest().getParameter("prescriptionId");
		
		String medicamentId = getRequest().getParameter("medicament");
		
		logger.info("MEDICAMENTE "+medicamentId);

		if (prescriptionId == null) {

			Long id = (Long) getRequest().getAttribute("userId");

			Doctor doctor = Registry.doctorFinder().findById(id);

			Long userId = PathVariable.getSecendIdFromUrl(getRequest().getRequestURL().toString());

			Patient patient = Registry.patientFinder().findById(userId);

			String additionalRight = getRequest().getParameter("add");

			boolean additionalR = false;

			if (additionalRight != null && additionalRight.equals("true"))
				additionalR = true;

			Timestamp dateOfIssue = new Timestamp(Calendar.getInstance().getTime().getTime());

			MedicalPrescription medicalPrescription = new MedicalPrescription(null, patient, doctor, dateOfIssue,
					additionalR);

			medicalPrescription.insert();
			
			redirectAttributes.addAttribute("prescriptionId", medicalPrescription.getId());
			
			logger.info(medicalPrescription.toString());
			
			Medicament medicament = Registry.medicamentFinder().findById(Long.valueOf(medicamentId));
			
			medicalPrescription.insertMedicament(medicament);
		
		} else {
			
			MedicalPrescription medicalPrescription = new MedicalPrescription();
			
			medicalPrescription.setId(Long.valueOf(prescriptionId));
			
			Medicament medicament = Registry.medicamentFinder().findById(Long.valueOf(medicamentId));
			
			medicalPrescription.insertMedicament(medicament);
			
			redirectAttributes.addAttribute("prescriptionId", prescriptionId);
			
		}

		

		

	}

}
