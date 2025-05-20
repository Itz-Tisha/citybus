package com.example.citybus.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

@Entity
public class passengers {
       
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int p_id;
	
	@Column( nullable = false)
	private String name;
	
	@Column(unique=true, nullable = false)
	private String email;
	
	@Column(name = "phoneNumber", nullable = false,length=10)
	private String phoneNumber;
	
	@OneToOne(mappedBy="passenger" , cascade=CascadeType.ALL)
	@JsonBackReference
	private ticket ticket;

	//chnages
	@ManyToOne
	@JoinColumn(name="bus_passenger")
	@JsonIgnore
	public Bus bus;
	//changes end
	
	public passengers() {
		super();
		// TODO Auto-generated constructor stub
	}

	public passengers(int p_id, String name, String email, String phoneNumber,
			com.example.citybus.entity.ticket ticket) {
		super();
		this.p_id = p_id;
		this.name = name;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.ticket = ticket;
	}

	public int getP_id() {
		return p_id;
	}

	public void setP_id(int p_id) {
		this.p_id = p_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public ticket getTicket() {
		return ticket;
	}

	public void setTicket(ticket ticket) {
		this.ticket = ticket;
	}
	
	//changes
	public void setBus(Bus bus) {
		this.bus=bus;
	}
	public Bus getBus() {
		return this.bus;
	}
	
}
