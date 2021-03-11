package tn.esprit.pi.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import tn.esprit.pi.entities.Feedback;
import tn.esprit.pi.entities.Meeting;
import tn.esprit.pi.entities.User;
import tn.esprit.pi.repositories.FeedbackRepository;
import tn.esprit.pi.repositories.MeetingRepository;


@Service
public class FeedbackService implements IFeedbackService{

	
	@Autowired
	FeedbackRepository feedbackrepository;
	@Autowired
	MeetingRepository meetingrepository;
	
	@Override
	public Feedback createFeedback(Feedback fb, int meeting) {
		Meeting m=meetingrepository.findById(meeting).get();
		fb.setMeeting(m);
		return feedbackrepository.save(fb);
	}

	@Override
	public List<Feedback> getAllFeedbacks() {
		List <Feedback> feedbacks=(List<Feedback>) feedbackrepository.findAll();

		return feedbacks;
	}

	@Override
	public void removeFeedback(int id) {
		// TODO Auto-generated method stub
		
		Feedback f=feedbackrepository.findById(id).get();
	
		feedbackrepository.delete(f);
	}

	@Override
	public Feedback getFeedbackById(int id) {
		// TODO Auto-generated method stub
		return feedbackrepository.findById(id).get();
	}

	@Override
	public int Countfeedbacks() {
		// TODO Auto-generated method stub
		List <Feedback> feedbacks=(List<Feedback>) feedbackrepository.findAll();

		return feedbacks.size();
	}

	@Override
	public List<Feedback> getFeedbackByMeeting(int meeting) {
		// TODO Auto-generated method stub
		List <Feedback> feedbacks=(List<Feedback>) feedbackrepository.getmeetingFeedbacks(meeting);
		

		return feedbacks;
	}


	
	
	

}
