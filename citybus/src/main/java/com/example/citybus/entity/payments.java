package com.example.citybus.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class payments {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long paymentsId;
	
	private double amount;
	
	private String paymentStatus;
	
	private LocalDateTime paymentDate;
	
	@OneToOne
	@JoinColumn(name="ticket_id")
	private ticket ticket;

	public payments() {
		super();
		// TODO Auto-generated constructor stub
	}

	public payments(Long paymentsId, double amount, String paymentStatus, LocalDateTime paymentDate,
			com.example.citybus.entity.ticket ticket) {
		super();
		this.paymentsId = paymentsId;
		this.amount = amount;
		this.paymentStatus = paymentStatus;
		this.paymentDate = paymentDate;
		this.ticket = ticket;
	}

	public Long getPaymentsId() {
		return paymentsId;
	}

	public void setPaymentsId(Long paymentsId) {
		this.paymentsId = paymentsId;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	public LocalDateTime getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(LocalDateTime paymentDate) {
		this.paymentDate = paymentDate;
	}

	public ticket getTicket() {
		return ticket;
	}

	public void setTicket(ticket ticket) {
		this.ticket = ticket;
	}

	
	
}
