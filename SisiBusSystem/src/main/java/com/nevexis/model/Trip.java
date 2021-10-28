package com.nevexis.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;

@Entity
public class Trip extends _BaseEntity {

	private String description;

	@ManyToOne
	@JoinColumn(name = "bus_id")
	private Bus bus;
	
//	@OneToMany(fetch = FetchType.EAGER)
//	@JoinColumn(name = "trip")
//	@JsonIgnore
	
	@OneToMany(mappedBy="trip",fetch = FetchType.EAGER)
	List<Transition> transitions = new ArrayList<>();
    
	@CachePut("tranitions")
	public List<Transition> getTransitions() {
		return transitions;
	}

	public void setTransitions(List<Transition> transitions) {
		this.transitions = transitions;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Bus getBus() {
		return bus;
	}

	public void setBus(Bus bus) {
		this.bus = bus;
	}

	

}
