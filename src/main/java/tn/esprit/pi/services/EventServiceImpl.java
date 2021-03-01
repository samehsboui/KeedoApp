package tn.esprit.pi.services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;

import tn.esprit.pi.entities.Event;
import tn.esprit.pi.entities.EventCategory;
import tn.esprit.pi.entities.Jackpot;
import tn.esprit.pi.entities.Participation;
import tn.esprit.pi.entities.ParticipationPK;
import tn.esprit.pi.entities.User;
import tn.esprit.pi.repositories.IEventRepository;
import tn.esprit.pi.repositories.IJackPotRepository;
import tn.esprit.pi.repositories.IParticipantRepository;

import java.util.Collections;
import java.util.Date;

@Service
public class EventServiceImpl  implements IEventService{

	
	@Autowired 
	private IEventRepository iEventRepository;
	
	@Autowired
	private IJackPotRepository iJackPotRepository;

	
	@Autowired
	private IParticipantRepository iParticipantRepository;
	
	//creating add method that insert  event into database
	@Override
	public void addEvent(Event e) {
		Jackpot j = new Jackpot();
		j.setSum(0);
		e.setJackpot(j);
		iEventRepository.save(e);
		iJackPotRepository.save(j);
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

		try {
				event.setTitle(e.getTitle());
				event.setAddress(e.getAddress());

		}catch(NullPointerException nullPointerException) {
			System.out.println(nullPointerException.getMessage());
		}
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
	public Event findEventByName(String name) {
		return iEventRepository.findEventByName(name);
	}

	
	//creating filterEvent method that filter event by his category
	@Override
	public List<Event> filterEvent(EventCategory category) {
		// TODO Auto-generated method stub
		return iEventRepository.filterByCategory(category);
	}

	
	//creating getEventsByViews method that retrieve top 3 events by views

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
		
		for(int i = 0 ; i <3 ; i++) {
			int max = sortedList.get(sortedList.size()-1);
			int ind = listId.get(listViews.indexOf(max));// prend nbre de vue et retourne id d'event corresspondant
			h.put(ind, max);

			System.out.println(ind +" "+ max);
			sortedList.remove(sortedList.size()-1);
			listViews.set(listViews.indexOf(max), -1);
		}
		return h;
	}

	
	//creating affectedEventUser that affect user to event
	@Override
	public String affecterEventUser(int iduser, int idevent) {
		
		Event event = iEventRepository.findById(idevent).get();
		int user = 1;

	
		int number = 0;
		Participation p = new Participation();
		ParticipationPK participationPK = new ParticipationPK();
		
		for(Participation part : iParticipantRepository.findAll()) {
			if(part.getEvent().getIdEvenement() == event.getIdEvenement()) {
				return "Vous avez deja participé a cette evenement";
			}
		}
		participationPK.setIdEvent(event.getIdEvenement());
		participationPK.setIdUser(iduser);
		p.setParticipationPK(participationPK);
		number = number +1;
		participationPK.setNumber(number);
		p.setParticipationDate(new Date().toString());
		iParticipantRepository.save(p);
		
		return "Affected successfully!!";
		
	}

	
	//creating displayBestEventsByViews that display most 3 views event
	@Override
	public List<String> displayBestEventsByViews() {
		
		List<String>list = new ArrayList<>();
		String s ="";
		List<Integer>listId = new ArrayList<>();
		List<Integer>listViews = new ArrayList<>();
		
		List<Event> listEvent = (List<Event>)iEventRepository.findAll();
		
		for(Event e : listEvent) {
			listId.add(e.getIdEvenement());
			listViews.add(e.getViews());
		
		}
		
		List<Integer> sortedList = new ArrayList<>(listViews);
		
		Collections.sort(sortedList);
		
		for(int i = 0 ; i<3 ; i++) {
			int max = sortedList.get(sortedList.size()-1);
			int ind = listId.get(listViews.indexOf(max));
			s = (i+1)+"--Event: "+iEventRepository.findById(ind).get().getTitle()+"with"+max+" views";
			list.add(s);
			sortedList.remove(sortedList.size()-1);
			listViews.set(listViews.indexOf(max), -1);
			}
		
		
		return list;
	}
	
	




}
