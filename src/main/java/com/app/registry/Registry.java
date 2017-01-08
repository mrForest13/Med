package com.app.registry;

import com.app.dao.IFinder;
import com.app.dao.IFinderAll;
import com.app.dao.IQueryFinder;
import com.app.dao.IUserFinder;
import com.app.model.lab.Lab;
import com.app.model.lab.finder.LabFinder;
import com.app.model.medical.MedicalPrescription;
import com.app.model.medical.Referal;
import com.app.model.medical.finder.MedicalPrescriptionFinder;
import com.app.model.medical.finder.ReferalFinder;
import com.app.model.session.Session;
import com.app.model.session.finder.SessionFinder;
import com.app.model.user.Doctor;
import com.app.model.user.Patient;
import com.app.model.user.finder.DoctorFinder;
import com.app.model.user.finder.PatientFinder;
import com.app.model.user.finder.UserFinder;
import com.app.model.visit.Visit;
import com.app.model.visit.VisitType;
import com.app.model.visit.finder.VisitFinder;
import com.app.model.visit.finder.VisitTypeFinder;

public class Registry {

	private static Registry soleInstance = new Registry();
	
	private IFinderAll<VisitType> visitTypeFinder = new VisitTypeFinder();
	private IQueryFinder<Visit> visitFinder = new VisitFinder();
	private IFinder<Patient> patientFinder = new PatientFinder();
	private IFinderAll<Doctor> doctorFinder = new DoctorFinder();
	private UserFinder userFinder = new UserFinder();
	private IFinderAll<Session> sessionFinder = new SessionFinder();
	private IUserFinder<MedicalPrescription> medicalPrescriptionFinder = new MedicalPrescriptionFinder();
	private IQueryFinder<Referal> referalFinder = new ReferalFinder();
	private IUserFinder<Lab> labFinder = new LabFinder();

	private Registry() {}
	
	public static Registry getInstance() {
		return soleInstance;
	}
		
	public static IFinderAll<VisitType> visitTypeFinder() {
		return getInstance().visitTypeFinder;
	}
	
	public static IQueryFinder<Visit> visitFinder() {
		return getInstance().visitFinder;
	}
	
	public static IFinder<Patient> patientFinder() {
		return getInstance().patientFinder;
	}
	
	public static IFinderAll<Doctor> doctorFinder() {
		return getInstance().doctorFinder;
	}
	
	public static UserFinder userFinder() {
		return getInstance().userFinder;
	}
	
	public static IFinderAll<Session> sessionFinder() {
		return getInstance().sessionFinder;
	}
	
	public static IUserFinder<MedicalPrescription> medicalPrescriptionFinder() {
		return getInstance().medicalPrescriptionFinder;
	}
	
	public static IQueryFinder<Referal> referalFinder() {
		return getInstance().referalFinder;
	}
	
	public static IUserFinder<Lab> labFinder() {
		return getInstance().labFinder;
	}
}
