package com.example.citybus.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

@Entity
public class route {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long routeId;
	
	@Column( nullable = false)
	private String fromPlace;
	
	@Column(nullable = false)
	private String toPlace;
	
	@Column( nullable = false)
	private double distance;
	
	@Column( nullable = false)
	private double duration;
	
	 @OneToOne
	 @JoinColumn(name = "bus_id")
	 @JsonBackReference
	 private Bus bus;

	public route() {
		super();
		// TODO Auto-generated constructor stub
	}

	public route( String fromPlace, String toPlace, double distance, double duration, Bus bus) {
		super();
		this.fromPlace = fromPlace;
		this.toPlace = toPlace;
		this.distance = distance;
		this.duration = duration;
		this.bus = bus;
	}

	public Long getRouteId() {
		return routeId;
	}

	public void setRouteId(Long routeId) {
		this.routeId = routeId;
	}

	public String getFromPlace() {
		return fromPlace;
	}

	public void setFromPlace(String fromPlace) {
		this.fromPlace = fromPlace;
	}

	public String getToPlace() {
		return toPlace;
	}

	public void setToPlace(String toPlace) {
		this.toPlace = toPlace;
	}

	public double getDistance() {
		return distance;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}

	public double getDuration() {
		return duration;
	}

	public void setDuration(double duration) {
		this.duration = duration;
	}

	public Bus getBus() {
		return bus;
	}

	public void setBus(Bus bus) {
		this.bus=bus;
	}
	
	
}
