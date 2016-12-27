package com.app.repository.factory;

import com.app.dao.IVisitTypeFinder;
import com.app.model.user.finder.Finder;
import com.app.model.user.finder.PatientFinder;
import com.app.model.visit.finder.VisitTypeFinder;

public class Registry {

	private static Registry soleInstance = new Registry();
	
	private IVisitTypeFinder visitTypeFinder = new VisitTypeFinder();
	private Finder patientFinder = new PatientFinder();

	private Registry() {}
	
	public static Registry getInstance() {
		return soleInstance;
	}
		
	public static IVisitTypeFinder visitTypeFinder() {
		return getInstance().visitTypeFinder;
	}
	
	public static Finder patientFinder() {
		return getInstance().patientFinder;
	}
}
