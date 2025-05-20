package com.example.citybus.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.citybus.entity.Bus;
import com.example.citybus.entity.passengers;
import com.example.citybus.entity.route;
import com.example.citybus.entity.ticket;
import com.example.citybus.service.BusService;

@RequestMapping("/citybus/")
@RestController
public class hello {
	
	BusService busservice;
	
	
	@Autowired
	public hello(BusService busservice) {
		super();
		this.busservice = busservice;
	}

	@PostMapping("/addbus")
	public ResponseEntity<String> add(@RequestBody Bus bus) {
		

		busservice.addbuswithroute(bus);
		return ResponseEntity.ok("Bus add succesfully");
		
	}
	
	@GetMapping("/findAllBus")
	public List<Bus> findAllbus() {
	List<Bus> buses = 	busservice.findbus();
	return buses;
		
	}
	
	@GetMapping("/findOneBus")
	public List<Bus> findParticularBus(@RequestParam String fromPlace , @RequestParam String toPlace){
		return  busservice.findParticularBus(fromPlace,toPlace);
		
		
	}
	
	@DeleteMapping("/deleteBus")
	public void DeleteBusById(@RequestParam int id) {
		busservice.deleteBusById(id);
	}
	
	@DeleteMapping("/deleteAllbus")
	public void deleteall() {
		busservice.deleteAll();
	}
	
	@PutMapping("/updatebusById")
	public ResponseEntity<String> updatebusById(@RequestParam int id,@RequestBody Bus bus) {

		busservice.updateById(id, bus);
		return ResponseEntity.ok("Bus updated");
     
	}
	
	//all opration of bus ends here
	
	
	//ticket operation start....
    
	@PostMapping("/bookTicket")
	public ResponseEntity<String> bookmyticket(@RequestBody passengers pass,@RequestParam String fromPlace,@RequestParam String toPlace,@RequestParam int busid) {
		
		busservice.bookmyticket(pass,fromPlace,toPlace,busid);
		return ResponseEntity.ok("Booking succesfull");
	}
	
	@GetMapping("findAlltickets")
	public List<ticket> findallticket(){
		return busservice.findallticket();
	}
	
	@GetMapping("findByPassengername")
	public List<ticket> findtickByName(@RequestParam String name){
		return busservice.findtickByname(name);
	}
	@GetMapping("findByPassId")
	public List<ticket> findtickBypassId(@RequestParam int id){
		return busservice.findtickBypassId(id);
	}
	@GetMapping("findticketById")
	public List<ticket> findByticketId(@RequestParam int id){
		return busservice.findByticketId(id);
	}
	@DeleteMapping("deleteticket")
	public void deleteticket(@RequestParam int tick_id) {
		 busservice.deleteticket(tick_id);
	}
	
	
	
	
	
	
	//end all ticket oprations...
	
	
	
	
	//start all passengers oprations...
	
	
	@GetMapping("findAllpass")
	public List<passengers> findall(){
		return busservice.findallpass();
	}
	
	@GetMapping("findpassByName")
	public List<passengers> findPassByName(@RequestParam String name){
		return busservice.findPassbyName(name);
	}
	
	@GetMapping("findpassBytickId")
	public List<passengers> findPassByticid(@RequestParam int ticid){
		return busservice.findPassByticid(ticid);
	}
	
	@GetMapping("findpassBypassId")
	public List<passengers> findPassBypassid(@RequestParam int pid){
		return busservice.findPassBypassid(pid);
	}
	@DeleteMapping("deletepassByid")
	public void deletepassById(@RequestParam int pid) {
		busservice.deletepassById(pid);
	}
	
	//end all pass opr

}
