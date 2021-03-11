package tn.esprit.pi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.pi.entities.Event;
import tn.esprit.pi.services.IDonationService;
import tn.esprit.pi.services.IEventService;

@RestController
public class DonationController {
	
	
	@Autowired
	IDonationService iDonationService;
	//creating put mapping that updates the event detail   
	@PostMapping("/event/donation-event/{eventid}/{amount}")  
	private ResponseEntity<String> DonationOfEvent(@PathVariable("eventid")int eventid, @PathVariable("amount")float amount )   
	{  
	
		String result = iDonationService.Donation(eventid,amount);  
	    return new ResponseEntity<String>(result,HttpStatus.OK);
	}
	

	
}
