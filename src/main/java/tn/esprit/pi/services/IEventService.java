package tn.esprit.pi.services;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;

import tn.esprit.pi.entities.Event;
import tn.esprit.pi.entities.EventCategory;

public interface IEventService {

	
	public void addEvent(Event e);
	public void deleteEvent(int id);
	public Event updateEvent(Event e , int id);
	public List<Event> getAllEvents();
	public Event getEventById(int id);
	
	public Event findEventByName(String name);
	
	public List<Event> filterEvent(EventCategory category);
	
	public String affecterEventUser(int iduser,int idevent);
	
	
	public Map<Integer,Integer>getEventsByViews();
	
	public List<String> displayBestEventsByViews() ;
	
	


}
