package tn.esprit.pi.services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

import tn.esprit.pi.entities.Event;
import tn.esprit.pi.entities.EventCategory;
import tn.esprit.pi.entities.User;
import tn.esprit.pi.repositories.IEventRepository;
import tn.esprit.pi.repositories.IUserRepository;

import java.util.Collections;

@Service
public class EventServiceImpl  implements IEventService{

	
	@Autowired 
	private IEventRepository iEventRepository;

	@Autowired 
	private IUserRepository iUserRepository;
	
	//creating add method that insert  event into database
	@Override
	public Event addEvent(Event e) {
		return iEventRepository.save(e) ;
	}

	//creating deleting method that remove   event by id  from database
	@Override
	public void deleteEvent(int id) {
		Event e = iEventRepository.findById(id).get();
		iEventRepository.delete(e);
		
	}


	//creating update method that upgrade  event from database
	@Override
	public Event updateEvent(Event e, int id) {
		Event event = iEventRepository.findById(id).get();
		event.setTitle(e.getTitle());
		event.setAddress(e.getAddress());
		event.setDate(e.getDate());
		event.setHour(e.getHour());
		event.setDescription(e.getDescription());
		event.setStatus(e.getStatus());
		return iEventRepository.save(event);
		
	}


	//creating getAll method that retrieve all events from database
	@Override
	public List<Event> getAllEvents() {
		
		List<Event>events = new ArrayList<Event>();
		iEventRepository.findAll().forEach(e ->events.add(e));
		return events;
	}
	//creating getByid method that retrieve event detail from database

	@Override
	public Event getEventById(int id) {
		return iEventRepository.findById(id).get();  

	}

	@Override
	public void affecterEventUser(int iduser, int idevent) {
		
		User user = iUserRepository.findById(iduser).orElse(null);
		Event event = iEventRepository.findById(idevent).orElse(null);
		
		List<Event>events = new ArrayList<>();
		System.out.println(user.getEvents());	
		
		Set<Event> eventsSet = new HashSet<>(events);

		if(user.getEvents().isEmpty() ) {
			eventsSet.add(event);
			user.setEvents(eventsSet);
		
		}else {
			
				user.getEvents().add(event);
					
			}
		iUserRepository.save(user);
		
	
	}

	@Override
	public Event findEventByName(String name) {
		return iEventRepository.findEventByName(name);
	}

	@Override
	public List<Event> filterEvent(EventCategory category) {
		// TODO Auto-generated method stub
		return iEventRepository.filterByCategory(category);
	}

	@Override
	public Map<Integer, Integer> getEventsByViews() {
		List<Integer>listId = new ArrayList<>();
		List<Integer>listViews = new ArrayList<>();
		Map<Integer,Integer> h = new HashMap<>();

		
		for(Event e : iEventRepository.findAll()) {
			
			listId.add(e.getIdEvenement());
			listViews.add(e.getViews());
		}
		
		List<Integer>sortedList = new ArrayList<>(listViews);
		Collections.sort(sortedList);
		for(int i = 0 ; i <1 ; i++) {
			
			int max = sortedList.get(sortedList.size()-1);
			int ind = listId.get(listViews.indexOf(max));// prend nbre de vue et retourne id d'event corresspondant
			h.put(ind, max);

			System.out.println(ind +" "+ max);
			sortedList.remove(sortedList.size()-1);
			listViews.set(listViews.indexOf(max), -1);
		}
		
		return h;
	}
	
	




}
