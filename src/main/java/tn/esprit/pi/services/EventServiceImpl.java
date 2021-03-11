package tn.esprit.pi.services;

import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
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

import tn.esprit.pi.entities.Advertisement;
import tn.esprit.pi.entities.Event;
import tn.esprit.pi.entities.EventCategory;
import tn.esprit.pi.entities.Jackpot;
import tn.esprit.pi.entities.Notification;
import tn.esprit.pi.entities.Participation;
import tn.esprit.pi.entities.ParticipationPK;
import tn.esprit.pi.entities.User;
import tn.esprit.pi.repositories.IAdvertisementRepository;
import tn.esprit.pi.repositories.IEventRepository;
import tn.esprit.pi.repositories.IJackPotRepository;
import tn.esprit.pi.repositories.INotificationRepository;
import tn.esprit.pi.repositories.IParticipantRepository;
import tn.esprit.pi.repositories.IUserRepository;

import java.util.Collections;
import java.util.Date;

@Service
public class EventServiceImpl  implements IEventService{

	
	@Autowired 
	private IEventRepository iEventRepository;
	
	@Autowired
	private IJackPotRepository iJackPotRepository;

	
	@Autowired 
	private IUserRepository iUserRepository;
	
	@Autowired
	private IParticipantRepository iParticipantRepository;
	
	@Autowired
	private INotificationRepository iNotificationRepository;
	
	
	@Autowired
	private IAdvertisementRepository iAdvertisementRepository;
	
	/**********************************Admin**********************************/

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

	@Override
	public void findEventById(int id) {
		 iEventRepository.findById(id).get();
	} 


	//creating update method that upgrade  event from database
	@Override
	public int updateEvent(Event e, int id) {
		 
			return iEventRepository.updateEvent(e.getTitle(),e.getDate() , e.getHour(), 
						e.getAddress(), e.getDescription(), e.getPlacesNbr(),e.getCategory(),e.getTicketPrice(),e.getStatus(),e.getImage(),e.getIdEvenement());
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
		int countView;
		Event e = iEventRepository.findById(id).get();
		if(e == null) return null;
		
		e.setViews(e.getViews()+1);
	    countView = iEventRepository.updateViewCountEvent(e.getViews()-1,e.getIdEvenement());
			countView++;
			
		return e ;  

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
		
		for(int i = 0 ; i <2 ; i++) {
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
	public String addParticipation(int iduser, int idevent) {
		
				Event event = iEventRepository.findById(idevent).get();
		
		User u = iUserRepository.findById(iduser).get();
		

		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
	
		int number = 0;
		Participation p = new Participation();
		ParticipationPK participationPK = new ParticipationPK();
		
		List<Participation>participations = (List<Participation>) iParticipantRepository.findAll();
		for(int i=0 ; i<participations.size();i++) {
			if(participations.get(i).getEvent().getIdEvenement() == idevent && 
					participations.get(i).getUser().getIdUser() == iduser)
		
				return "You are already participate !!";
			
		}
		
		
		if(event.getPlacesNbr() >0) {
			float discPercent =(100f-event.getDiscountPercentage())/100f;
			float newPrice =event.getTicketPrice() * discPercent;// pourcentage de réduction ticket		}
		
		participationPK.setIdEvent(event.getIdEvenement());
		participationPK.setIdUser(iduser);
		p.setParticipationDate(dateFormat.format(date));	
		event.setPlacesNbr(event.getPlacesNbr() - 1);

		event.setParticipantsNbr(event.getParticipantsNbr() + 1);
		
		event.setCollAmount(event.getCollAmount()+ newPrice);
		p.setPrice(newPrice);
		p.setParticipationPK(participationPK);
		p.setParticipationDate(new Date().toString());
		
		
		iParticipantRepository.save(p);
		iEventRepository.save(event);
		}
		
		
		return "Affected successfully with discount percentage";
		
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
		
		for(int i = 0 ; i<2 ; i++) {
			int max = sortedList.get(sortedList.size()-1);
			int ind = listId.get(listViews.indexOf(max));
			s = (i+1)+"--Event: "+iEventRepository.findById(ind).get().getTitle()+"  = with"+max+" views";
			list.add(s);
			sortedList.remove(sortedList.size()-1);
			listViews.set(listViews.indexOf(max), -1);
			}
		
		
		return list;
	}
	
	public String affecterCategoryEvent(String category, int idevent) {
		Event event = iEventRepository.findById(idevent).get();
		String msg=" ";
		
		//bch n7awl men string l enum => CategoryEvent.valueOf(category)
		try {
		for(EventCategory c : EventCategory.values()) {
			if(c == EventCategory.valueOf(category)) {
				event.setCategory(EventCategory.valueOf(category));
				iEventRepository.save(event);
				return msg ="Category of Event Affected successfully to event!";
			}
		}
	}catch(Exception e) {
			 msg="Failed to affected Category";
		}
		return msg;
	}

	@Override
	public String affecterEventAdv(int idavert, int idevent) {
		Event event = iEventRepository.findById(idevent).get();
		
		Advertisement adv= iAdvertisementRepository.findById(idavert).get();
		
		adv.setEvent(event);
		
		iAdvertisementRepository.save(adv);
		return "Event affected succesfully to advertisement";
	
	}

	@Override
	public List<Event> upcomeEvents() {
		List<Event> list= iEventRepository.upcomingEvents();
		
		return list;	
			}


	
	@Override
	public void refundUsers(int eid) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		java.util.Date date = new java.util.Date();
		
		Event ev = iEventRepository.findById(eid).get();
		User u = new User();
		u = iUserRepository.findById(2).get();

		List<Participation> participationsOfEvent = iParticipantRepository.Participations(ev);
		System.out.println("hiii==");
		Notification n = new Notification();

		for(Participation p : participationsOfEvent) {
			//int u = new User();//user id
			float refundedAmount = p.getPrice();
			ev.setCollAmount(ev.getCollAmount()-refundedAmount);
			//iUserRepository.save(u);
			System.out.println("hiiiiii==");

			iParticipantRepository.delete(p);
			
			
			iEventRepository.save(ev);
			

			//n.setEvent(ev);
			n.setUser(u);
			LocalTime localTime = LocalTime.now();

			n.setTime(Time.valueOf(localTime));
			n.setSubject("Canceled Event");
			n.setDescription("Dear "+u.getLastName()+" "+u.getLastName()+""
					+ ",we regret to announce that the event "+ev.getTitle()+" you want to attend has been canceled for some reason."
					+ " That's why, we have refunded your ticket price. If there is a problem, do not hesitate to contact us."
					+ " Thank you.");
			n.setDate(date);
			
			n.setStatus("Not Seen Yet");

			
			iNotificationRepository.save(n);
		
		}
	}



}
