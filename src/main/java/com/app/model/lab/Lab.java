package com.app.model.lab;

import java.util.List;

import com.app.model.lab.finder.LabFinder;
import com.app.model.visit.Visit;
import com.app.registry.Registry;

public class Lab {

	private Long id;
	private Visit visit;
	private List<Sample> sample;
	private boolean isAdded;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Visit getVisit() {

		if (visit.getDoctor() == null)
			visit = Registry.visitFinder().findById(visit.getId());

		return visit;
	}

	public void setVisit(Visit visit) {
		this.visit = visit;
	}

	public List<Sample> getSample() {
		
		if(sample==null) sample = LabFinder.findByLabId(id);
		
		return sample;
	}

	public void setSample(List<Sample> sample) {
		this.sample = sample;
	}

	public boolean isAdded() {
		return isAdded;
	}

	public void setAdded(boolean isAdded) {
		this.isAdded = isAdded;
	}

	public Lab(Long id, Visit visit, List<Sample> sample, boolean isAdded) {
		super();
		this.id = id;
		this.visit = visit;
		this.sample = sample;
		this.isAdded = isAdded;
	}

	public Lab() {
	}

	@Override
	public String toString() {
		return "Lab [id=" + id + ", visit=" + visit + ", sample=" + sample + ", isAdded=" + isAdded + "]";
	}

}
