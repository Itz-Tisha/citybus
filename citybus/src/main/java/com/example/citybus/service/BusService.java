package com.example.citybus.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.citybus.DAO.busDao;

import com.example.citybus.entity.Bus;
import com.example.citybus.entity.passengers;
import com.example.citybus.entity.route;
import com.example.citybus.entity.ticket;


@Service
public class BusService {
     
	busDao busdao;
	
	@Autowired
	public BusService(busDao busdao) {
		super();
		this.busdao = busdao;
	}

	  @Scheduled(cron = "0 0 0 * * ?") // Runs every day at midnight
	    public void resetSeats() {
	        List<Bus> buses = busdao.findbus();
	        for (Bus bus : buses) {
	            bus.setAvailableSeats(0);
	        }
	        busdao.updateAllBus(buses);
	        System.out.println("Seats reset successfully!");
	    }
	public void addbuswithroute(Bus bus) {
		busdao.addbusroute(bus);
		
	}

	
	public List<Bus> findbus(){
		return busdao.findbus();
	}
	
	public List<Bus> findParticularBus(String fromPlace , String toPlace){
		return busdao.findParticularBus(fromPlace,toPlace);
	}
	
	public void deleteBusById(int id) {
		busdao.deleteBusById(id);
	}
	
	public void deleteAll() {
		busdao.deleteAll();
	}
	
	public void updateById(int id,Bus bus) {
		busdao.updateById(id,bus);
	}
	
	//all operation of bus ends here 
	
	//ticket...
	public void bookmyticket(passengers pass,String fromPlace,String toPlace,int busid) {
		busdao.bookmyticket(pass,fromPlace,toPlace,busid);
	}
	
	public List<ticket> findallticket() {
		return busdao.findallticket();
	}
	
	public List<ticket> findtickByname(String name){
		return busdao.findtickByname(name);
	}
	public List<ticket> findtickBypassId(int id){
		return busdao.findtickBypassId(id);
	}
	public List<ticket> findByticketId(int id){
		return busdao.findByticketId(id);
	}
	public void deleteticket(int tick_id) {
		busdao.deleteTicket(tick_id);
	}
	
	//ticket ends
	
	
	
	//pass start
	
	
	public List<passengers> findallpass(){
		return busdao.findallpass();
	}
	
	
	public List<passengers> findPassbyName( String name){
		return busdao.findPassbyName(name);
	}
	
	
	public List<passengers> findPassByticid(int ticid){
		return busdao.findPassByticid(ticid);
	}
	
	
	public List<passengers> findPassBypassid( int pid){
		return busdao.findPassBypassid(pid);
	}
	public void deletepassById(int pid) {
		busdao.deletepassById(pid);
	}
	
	//pass ends
}
