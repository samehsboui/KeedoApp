package tn.esprit.pi.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.pi.entities.Event;
import tn.esprit.pi.repositories.IEventRepository;

@Service
public class EventServiceImpl  implements IEventService{

	
	@Autowired 
	IEventRepository iEventRepository;
	@Override
	public Event addEvent(Event e) {
		return iEventRepository.save(e) ;
	}

	@Override
	public void deleteEvent(int id) {
		Event e = iEventRepository.findById(id).get();
		iEventRepository.delete(e);
		
	}

	@Override
	public Event updateEvent(Event e, int id) {
		Event event = iEventRepository.findById(id).get();
		event.setTitle(e.getTitle());
		event.setAddress(e.getAddress());
		event.setStartDate(e.getStartDate());
		event.setEndDate(e.getEndDate());
		event.setDescription(e.getDescription());
		event.setStatus(e.getStatus());
		return iEventRepository.save(event);
		
	}

	@Override
	public List<Event> getAllEvents() {
		
		List<Event>events = new ArrayList<Event>();
		iEventRepository.findAll().forEach(e ->events.add(e));
		return events;
	}

	@Override
	public Event getEventById(int id) {
		return iEventRepository.findById(id).get();  

	}


}
