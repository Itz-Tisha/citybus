package com.example.citybus.entity;

import java.util.Date;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalTime;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
public class Bus {
       
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int bus_id;
	
	 @Column(unique = true,nullable = false)
	private String bus_Number;
	 
	 @Column(nullable = false)
	private int capacity;
	 
	 @Column(nullable = false)
	private int availableSeats;
	
	 @Column(nullable = false)
	 private int ticketprice;
	 
	 
	 @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss")
	 @Column(nullable = false)
	 private LocalTime departureTime;
	 
	 
	 @OneToOne(mappedBy = "bus", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
	 @JsonManagedReference
	public  route route_details;
	
	 //changes
	 @OneToMany(mappedBy="bus" ,cascade=CascadeType.ALL,fetch=FetchType.LAZY,orphanRemoval = true)
	 private List<passengers> passenger_details = new ArrayList<>();
	 
	@OneToMany(mappedBy ="bus" ,cascade =CascadeType.ALL,fetch=FetchType.LAZY, orphanRemoval = true)
	private List<ticket> tickets = new ArrayList<>();
	
	
	
	 
	
	
	public Bus( String bus_Number, int capacity, int availableSeats, route routes,int price,LocalTime departureTime) {
		super();
		this.bus_Number = bus_Number;
		this.capacity = capacity;
		this.availableSeats = availableSeats;
		this.route_details = routes;
		this.tickets = new ArrayList<>();
		this.ticketprice=price;
		this.departureTime=departureTime;
	}

	public Bus() {
		super();
		// TODO Auto-generated constructor stub
		this.tickets = new ArrayList<>();
	}

	public int getBus_id() {
		return bus_id;
	}

	public void setBus_id(int bus_id) {
		this.bus_id = bus_id;
	}

	public String getBus_Number() {
		return bus_Number;
	}

	public void setBus_Number(String bus_Number) {
		this.bus_Number = bus_Number;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public int getAvailableSeats() {
		return availableSeats;
	}

	public void setAvailableSeats(int availableSeats) {
		this.availableSeats = availableSeats;
	}

	public route getRoute_details() {
		return route_details;
	}

	public void setRoute_details(route routes) {
		this.route_details = routes;
	}

	public List<ticket> getTickets() {
		return tickets;
	}

	//changes
	public void setTickets(ticket tickets) {
		this.tickets.add(tickets);
		
	}
	
	//changes
	public void setPassenger_details(passengers pass) {
		this.passenger_details.add(pass);
	}
	public List<passengers> getPassenger_details(){
		return this.passenger_details;
	}
	//changes end
	
	public void setTicketprice(int price) {
		this.ticketprice=price;
	}
	public int getTicketprice() {
		return this.ticketprice;
	}

	// Consistent naming for departureTime field
	public LocalTime getDepartureTime() {
	    return departureTime;
	}

	public void setDepartureTime(LocalTime departureTime) {
	    this.departureTime = departureTime;
	}

	
}
