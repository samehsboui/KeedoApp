package tn.esprit.pi.services;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import tn.esprit.pi.entities.Event;
import tn.esprit.pi.entities.Meeting;

public interface IMeetingService {
	public int addMeeting(Meeting e);
	public void deleteMeeting(int id);
	public Meeting updateMeeting( int id);
	public List<Meeting> getAllMeeting();
	public Meeting getMeetingById(int id);
	
}
