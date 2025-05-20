package com.example.citybus.DAO;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import com.example.citybus.entity.Bus;
import com.example.citybus.entity.passengers;
import com.example.citybus.entity.route;
import com.example.citybus.entity.ticket;


import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
@Repository
public class busDao {
   
	EntityManager e1;

	@Autowired
	public busDao(EntityManager e1) {
		super();
		this.e1 = e1;
	}


	@Transactional
	public List<Bus> findbus(){
		Query q = e1.createQuery("from Bus");
		List<Bus> buses = q.getResultList();
		return buses;
	}
	
	@Transactional
	public void addbusroute(Bus bus) {
	    // Check for duplicate bus number
	    String query = "SELECT COUNT(b) FROM Bus b WHERE b.bus_Number = :bus_Number";
	    Long count = (Long) e1.createQuery(query)
	            .setParameter("bus_Number", bus.getBus_Number())
	            .getSingleResult();
	    
	    if (count > 0) {
	        throw new RuntimeException("Bus with this number already exists!");
	    }

	    // Save the bus and route
	    e1.persist(bus);
	    bus.getRoute_details().setBus(bus);
	}


	
	

	
	
	@Transactional
	public List<Bus> findParticularBus(String fromPlace, String toPlace) {
		
	    String query = "SELECT b FROM Bus b JOIN b.route_details r " +
	            "WHERE r.fromPlace = :fromPlace AND r.toPlace = :toPlace " +
	            "AND b.availableSeats >0";

	    Query q = e1.createQuery(query);
	    q.setParameter("fromPlace", fromPlace);
	    q.setParameter("toPlace", toPlace);

	    List<Bus> buses = q.getResultList();

	    
	    buses.forEach(bus -> System.out.println(bus.toString()));

	    return buses;
	}

	
	@Transactional
	public void updateAllBus(List<Bus> buses) {
		for(Bus bus:buses) {
			e1.merge(bus);
		}
	}
	
	@Transactional
	public void deleteBusById(int id) {
		Bus bus = e1.find(Bus.class, id);
		if(bus==null) {
			 throw new RuntimeException("Bus not found with id: " + id);
		}
		e1.remove(bus);
	}
	
	@Transactional
	public void deleteAll() {
		e1.createQuery("DELETE FROM ticket").executeUpdate();
        e1.createQuery("DELETE FROM route").executeUpdate();

       e1.createQuery("DELETE FROM passengers").executeUpdate();
        e1.createQuery("DELETE FROM Bus").executeUpdate();
	}
	

	
	@Transactional
	public void updateById(int id, Bus updatedBus) {
	    Bus existingBus = e1.find(Bus.class, id);

	    if (existingBus == null) {
	        throw new RuntimeException("Bus not found with id: " + id);
	    }

	    // Update simple fields
	    existingBus.setBus_Number(updatedBus.getBus_Number());
	    existingBus.setCapacity(updatedBus.getCapacity());
	    existingBus.setAvailableSeats(updatedBus.getAvailableSeats());
	    existingBus.setTicketprice(updatedBus.getTicketprice());
	    existingBus.setDepartureTime(updatedBus.getDepartureTime());

	    // Handle route update (if provided)
	    if (updatedBus.getRoute_details() != null) {
	        route updatedRoute = updatedBus.getRoute_details();
	        route existingRoute = existingBus.getRoute_details();

	        if (existingRoute == null) {
	            existingBus.setRoute_details(updatedRoute);
	        } else {
	            existingRoute.setFromPlace(updatedRoute.getFromPlace());
	            existingRoute.setToPlace(updatedRoute.getToPlace());
	            existingRoute.setDistance(updatedRoute.getDistance());
	            existingRoute.setDuration(updatedRoute.getDuration());
	        }
	    }

	    e1.merge(existingBus);
	}

	
	
	
	
	
	
	
	
	
	//all bus operation ends here
	
	
	@Transactional
	public void bookmyticket(passengers pass, String fromPlace, String toPlace, int busid) {
	    String query = "select b from Bus b JOIN b.route_details r where r.fromPlace =: fromPlace AND r.toPlace =: toPlace AND b.bus_id =: busid AND b.availableSeats >0";
	    Query q = e1.createQuery(query);
	    q.setParameter("fromPlace", fromPlace);
	    q.setParameter("toPlace", toPlace);
	    q.setParameter("busid", busid);
	    
	    List<Bus> buses = q.getResultList();
	    
	    if (buses.isEmpty()) {
	        System.out.println("No matching bus found!");
	        return;
	    }
	    
	    Bus bus = buses.get(0); 
	    int seat = bus.getAvailableSeats();
	    seat--;
	    bus.setAvailableSeats(seat);
	    ticket ticket = new ticket();
	    route route_details = bus.getRoute_details();
	    ticket.setBus(bus);
	    ticket.setRoute_details(route_details);
	    pass.setBus(bus);
	    ticket.setPassenger(pass);
	    pass.setTicket(ticket);
	    bus.setTickets(ticket);
	    bus.setPassenger_details(pass);
	    
	    e1.persist(ticket);
	    e1.persist(pass);
	    System.out.println("Ticket booked successfully!");
	}
	
	@Transactional
	public List<ticket> findallticket(){
		TypedQuery<ticket> q = e1.createQuery("from ticket",ticket.class);
		List<ticket> list = q.getResultList();
		return list;
	}
	@Transactional
	public List<ticket> findtickByname(String name){
		String q = "select t from ticket t where t.passenger.name = :name";
		return e1.createQuery(q,ticket.class).setParameter("name", name).getResultList();
	}
	@Transactional
	public List<ticket> findtickBypassId(int id){
		String q = "select t from ticket t where t.passenger.p_id = :id";
		return e1.createQuery(q,ticket.class).setParameter("id", id).getResultList();
	}
	@Transactional
	public List<ticket> findByticketId(int id){
		String q = "select t from ticket t where t.ticketId = :id";
		return e1.createQuery(q,ticket.class).setParameter("id", id).getResultList();
	}
	
	
	
	@Transactional
	public void deleteTicket(int ticketId) {
	    ticket ticket = e1.find(ticket.class, ticketId);
	    String q2 = "select t.bus from ticket t where t.ticketId=:tid";
		Bus bus = e1.createQuery(q2,Bus.class).setParameter("tid", ticketId).getSingleResult();
		int seat = bus.getAvailableSeats();
		seat++;
		bus.setAvailableSeats(seat);
	    if (ticket == null) {
	        throw new RuntimeException("Ticket not found with id: " + ticketId);
	    }
	    e1.remove(ticket);  
	}

	
	
	
	
	
	//ticket ends
	
	
	//pass start
	@Transactional
	public List<passengers> findallpass(){
		TypedQuery<passengers> q = e1.createQuery("from passengers",passengers.class);
		List<passengers> list = q.getResultList();
		return list;
	}
	
	@Transactional
	public List<passengers> findPassbyName(String name){
		String q = "select p from passengers p where p.name=:name";
		return e1.createQuery(q,passengers.class).setParameter("name", name).getResultList();
	}
	
	@Transactional
	public List<passengers> findPassByticid(int ticid){
		String q = "select p from passengers p where p.ticket.ticketId = :ticid";
		return e1.createQuery(q,passengers.class).setParameter("ticid", ticid).getResultList();
	}
	
	@Transactional
	public List<passengers> findPassBypassid( int pid){
		String q = "select p from passengers p where p.p_id = :pid";
		return e1.createQuery(q,passengers.class).setParameter("pid", pid).getResultList();
	}
	@Transactional
	public void deletepassById(int pid) {
		passengers pass = e1.find(passengers.class, pid);
		 if (pass == null) {
		        throw new RuntimeException("passenger not found");
		    }
		e1.remove(pass);
	}
	
	//pass ends

}
