package tn.esprit.pi.services;

import java.util.List;

import org.springframework.http.ResponseEntity;

import tn.esprit.pi.entities.Event;

public interface IEventService {

	
	public Event addEvent(Event e);
	public void deleteEvent(int id);
	public Event updateEvent(Event e , int id);
	public List<Event> getAllEvents();
	public Event getEventById(int id);
	

}
