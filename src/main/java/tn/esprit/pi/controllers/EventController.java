package tn.esprit.pi.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.pi.entities.Event;
import tn.esprit.pi.services.EventServiceImpl;

@RestController
public class EventController {
	
	@Autowired  
	EventServiceImpl eventServiceImpl;  
	//creating a get mapping that retrieves all the event detail from the database   
	@GetMapping("/event/get-all-events")  
	private List<Event> getAllEvents()   
	{  
		return eventServiceImpl.getAllEvents();  
	}  
	//creating a get mapping that retrieves the detail of a specific event  
	@GetMapping("/event/detail-event/{eventid}")  
	private Event getEvent(@PathVariable("eventid") int eventid)   
	{  
		return eventServiceImpl.getEventById(eventid);  
	}  
	//creating a delete mapping that deletes a specified event  
	@DeleteMapping("/event/delete-event/{eventid}")  
	private void deleteEvent(@PathVariable("eventid") int eventid)   
	{  
		eventServiceImpl.deleteEvent(eventid);  
	}  
	//creating post mapping that post the event detail in the database  
	@PostMapping("/event/add-event")  
	private int addEvent(@RequestBody Event events)   
	{  
		eventServiceImpl.addEvent(events);  
		return events.getIdEvenement();  
	}  
	//creating put mapping that updates the event detail   
	@PutMapping("/event/update-event/{eventid}")  
	private Event updateEvent(@RequestBody Event events, @PathVariable("eventid")int eventid)   
	{  
	
		eventServiceImpl.updateEvent(events,eventid);  
		return events;  
	}  
	
	
}
