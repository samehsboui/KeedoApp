package tn.esprit.pi.services;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.pi.entities.AppointmentStatus;
import tn.esprit.pi.entities.Event;
import tn.esprit.pi.entities.Meeting;
import tn.esprit.pi.entities.User;
import tn.esprit.pi.repositories.IMeetingRepository;
import tn.esprit.pi.repositories.IUserRepository;

@Service
public class MeetingServiceImpl  implements IMeetingService{

	@Autowired
	private IMeetingRepository iMeetingRepository;
	
	@Autowired
	private IUserRepository iUserRepository;


	
	
	@Override
	public void deleteMeeting(int id) {
		iMeetingRepository.deleteById(id);
	}

	@Override
	public Meeting updateMeeting(int id) {
		Meeting m = iMeetingRepository.findById(id).get();
		return iMeetingRepository.save(m);
		
	
	}

	@Override
	public List<Meeting> getAllMeeting() {
		
		List<Meeting>meetings = (List<Meeting>) iMeetingRepository.findAll();
		List<Meeting> result =  new ArrayList<Meeting>();
		meetings.forEach(m ->result.add(m));
		return result;
	}

	@Override
	public Meeting getMeetingById(int id) {
		// TODO Auto-generated method stub
		return iMeetingRepository.findById(id).get();
	}

	@Override
	public void createNewAppointment(int parents, int kindergardenId, int customerId, LocalDateTime start) {
		
	
		
		
	}

	@Override
	public List<Meeting> getMeetingByParentId(int userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Meeting> getMeetingByKindergardenId(int kindergardenId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Meeting> getMeetingByKindergardenAtDay(int kindergardenId, LocalDate day) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Meeting> getMeetingByCustomerAtDay(int kindergardenId, LocalDate day) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Meeting> getConfirmedMeetingByParentId(int userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Meeting> getCanceledMeetingsByParentIdForCurrentMonth(int userId) {
		// TODO Auto-generated method stub
		return null;
	}


	
	
	
	
	
	
	
	
	
	
	

}
