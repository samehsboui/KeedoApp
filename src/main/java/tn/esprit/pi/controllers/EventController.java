package tn.esprit.pi.controllers;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import org.hibernate.search.query.dsl.QueryBuilder;

import org.hibernate.search.jpa.FullTextEntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.pi.entities.Event;
import tn.esprit.pi.entities.EventCategory;
import tn.esprit.pi.repositories.IEventRepository;
import tn.esprit.pi.services.EventServiceImpl;

@RestController
public class EventController {


	@Autowired  
	private EventServiceImpl eventServiceImpl;  
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
	//creating get mapping that getEventByName   

	@GetMapping("/retrieve-Event-ByName/{name}")
	public Event getEventByName(@PathVariable String name) {
		 Event ev = eventServiceImpl.findEventByName(name);
		return ev;
		}
	@GetMapping("/retrieve-Event-ByCategory/{category}")
	public List<Event> getEventByCategory(@PathVariable EventCategory category) {
		 List<Event> ev = eventServiceImpl.filterEvent(category);
		return ev;
		}
	
	@GetMapping("/bestEventsByViews")
	public Map<Integer, Integer> bestEventsByViews(){
		return eventServiceImpl.getEventsByViews();
		}
}