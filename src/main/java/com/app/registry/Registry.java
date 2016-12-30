package com.app.registry;

import com.app.dao.IFinder;
import com.app.model.session.Session;
import com.app.model.session.finder.SessionFinder;
import com.app.model.user.Doctor;
import com.app.model.user.Patient;
import com.app.model.user.finder.DoctorFinder;
import com.app.model.user.finder.Finder;
import com.app.model.user.finder.PatientFinder;
import com.app.model.user.finder.UserFinder;
import com.app.model.user.finder.IUserFinder;
import com.app.model.visit.Visit;
import com.app.model.visit.VisitType;
import com.app.model.visit.finder.VisitFinder;
import com.app.model.visit.finder.VisitTypeFinder;

public class Registry {

	private static Registry soleInstance = new Registry();
	
	private IFinder<VisitType> visitTypeFinder = new VisitTypeFinder();
	private IFinder<Visit> visitFinder = new VisitFinder();
	private IUserFinder<Patient> patientFinder = new PatientFinder();
	private IUserFinder<Doctor> doctorFinder = new DoctorFinder();
	private UserFinder userFinder = new UserFinder();
	private IFinder<Session> sessionFinder = new SessionFinder();

	private Registry() {}
	
	public static Registry getInstance() {
		return soleInstance;
	}
		
	public static IFinder<VisitType> visitTypeFinder() {
		return getInstance().visitTypeFinder;
	}
	
	public static IFinder<Visit> visitFinder() {
		return getInstance().visitFinder;
	}
	
	public static IUserFinder<Patient> patientFinder() {
		return getInstance().patientFinder;
	}
	
	public static IUserFinder<Doctor> doctorFinder() {
		return getInstance().doctorFinder;
	}
	
	public static UserFinder userFinder() {
		return getInstance().userFinder;
	}
	
	public static IFinder<Session> sessionFinder() {
		return getInstance().sessionFinder;
	}
}
