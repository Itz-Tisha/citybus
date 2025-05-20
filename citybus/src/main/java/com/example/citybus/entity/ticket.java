package com.example.citybus.entity;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

@Entity
public class ticket {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long ticketId;
	
	
	@Column(nullable = false)
	private LocalDateTime bookingdate;
	//add time also
	@OneToOne(cascade=CascadeType.ALL,orphanRemoval = true)
	@JoinColumn(name="passenger_id",referencedColumnName = "p_id")
	@JsonManagedReference 
	private passengers passenger;
	
	@ManyToOne
	@JoinColumn(name="bus_id")
	@JsonIgnore
	private Bus bus;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="route_id")
	private route route_details;
	
	

	public ticket() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ticket(Long ticketId, LocalDateTime bookingdate, passengers passenger, Bus bus, route route_details) {
		super();
		this.ticketId = ticketId;
		this.bookingdate = bookingdate;
		this.passenger = passenger;
		this.bus = bus;
		this.route_details = route_details;
		
	}

	public Long getTicketId() {
		return ticketId;
	}

	public void setTicketId(Long ticketId) {
		this.ticketId = ticketId;
	}

	public LocalDateTime getBookingdate() {
		return bookingdate;
	}

	public void setBookingdate(LocalDateTime bookingdate) {
		this.bookingdate = bookingdate;
	}

	public passengers getPassenger() {
		return passenger;
	}

	public void setPassenger(passengers passenger) {
		this.passenger = passenger;
	}

	public Bus getBus() {
		return bus;
	}

	public void setBus(Bus bus) {
		this.bus = bus;
	}

	public route getRoute_details() {
		return route_details;
	}

	public void setRoute_details(route route_details) {
		this.route_details = route_details;
	}

	
	
	

}
