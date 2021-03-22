package tn.esprit.pi.services;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import tn.esprit.pi.entities.Event;
import tn.esprit.pi.entities.Meeting;
import tn.esprit.pi.entities.User;

public interface IMeetingService {
	
	
    //void createNewAppointment(int parentId, int kindergardenId, int customerId, LocalDateTime start);

      void createNewAppointment( int parentId, int kindergardenId, int customerId, LocalDateTime start);
  	
    public void deleteMeeting(int id);
	
    public Meeting updateMeeting( int id);
	
    public List<Meeting> getAllMeeting();
	
    public Meeting getMeetingById(int id);
    
    List<Meeting> getMeetingByParentId(int userId);

    List<Meeting> getMeetingByKindergardenId(int kindergardenId);

    List<Meeting> getMeetingByKindergardenAtDay(int kindergardenId, LocalDate day);

    List<Meeting> getMeetingByCustomerAtDay(int kindergardenId, LocalDate day);

    List<Meeting> getConfirmedMeetingByParentId(int userId);

    List<Meeting> getCanceledMeetingsByParentIdForCurrentMonth(int userId);

   
    
    
    
	
}
