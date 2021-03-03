package tn.esprit.pi.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import tn.esprit.pi.entities.Bus;
import tn.esprit.pi.entities.Driver;
import tn.esprit.pi.services.BusService;



@RestController
@RequestMapping("/bus")
public class BusController {
	
	@Autowired
	private BusService busService;
	

	 @GetMapping("/showBus")
	    public List<Bus> showNewDrivereForm() {
	        return busService.getAllBus();
	    }
	
	 @PostMapping("/saveBus/{idDriver}/{idU}")
		public Bus AffectBusDriver(@RequestBody Bus bus, @PathVariable("idDriver")int idDriver,@PathVariable("idU")int idU)   
		{  
			 
			return busService.AffectBusDriver(bus, idDriver,idU);
		}
	 
	 
	
		/*
		 * @RequestMapping(value = "/add", method = RequestMethod.POST)
	Bus add(@RequestBody Bus u) {
		
		Bus newBus = new Bus();
		Driver driver = this.typecongeservice.findbyname(u.getCauseConge());
		newconge.setTypeconge(typeconge);
		newconge.setCauseRefus(u.getCauseRefus());
		newconge.setDateDebut(u.getDateDebut());

		newconge.setReponse(u.getReponse());
		newconge.setNombreJours(u.getNombreJours());
		UserManager user = this.userservice.find(SecurityContextHolder.getContext().getAuthentication().getName());
		newconge.setUsermanager(user);
		 return service.add(newconge);
		
		 */
		
		
		 
	/*	@RequestMapping(value = "/deleteBus/{id}", method = RequestMethod.DELETE)
		    public void deleteDriver(@PathVariable int id) {

			busService.deleteBusById(id);
	           
		}*/

		@DeleteMapping("/deleteBus/{id}")
		public void deleteDriver(@PathVariable("id") int id) {
			busService.deleteBusById(id);
		}
		

		@PutMapping("/updateBus/{idBus}")  
		private Bus updateBus(@RequestBody Bus bus, @PathVariable("idBus")int idBus)   
		{  
		
			busService.updateBus(bus, idBus) ;
			return bus;  
		} 
		
		
		@GetMapping("/showBusByDriver/{firstName}")
		public List<Bus> getBusByDriver(@PathVariable String firstName) {
			 List<Bus> bus = busService.getBusByDriver(firstName);
			return bus;
			}
		
}
