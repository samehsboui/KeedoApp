package tn.esprit.pi.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import tn.esprit.pi.entities.Feedback;
import tn.esprit.pi.repositories.FeedbackRepository;


@Service
public class FeedbackService implements IFeedbackService{

	
	@Autowired
	FeedbackRepository feedbackrepository;
	
	@Override
	public Feedback createFeedback(Feedback fb) {
		
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
