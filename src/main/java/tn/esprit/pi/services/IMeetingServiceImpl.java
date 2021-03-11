package tn.esprit.pi.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.pi.entities.Event;
import tn.esprit.pi.entities.Meeting;
import tn.esprit.pi.repositories.IMeetingRepository;

@Service
public class IMeetingServiceImpl  implements IMeetingService{

	@Autowired
	private IMeetingRepository iMeetingRepository;
	@Override
	public int addMeeting(Meeting m) {
		iMeetingRepository.save(m);
		return m.getIdMeeting();
	}

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

}
