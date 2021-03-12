package tn.esprit.pi.services;
 
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Service;


import tn.esprit.pi.entities.Feedback;
import tn.esprit.pi.entities.Meeting;

import tn.esprit.pi.repositories.FeedbackRepository;
import tn.esprit.pi.repositories.MeetingRepository;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

@Service
public class FeedbackService implements IFeedbackService{

	
	@Autowired
	FeedbackRepository feedbackrepository;
	@Autowired
	MeetingRepository meetingrepository;
	

	  private final static String ACCOUNT_SID = "ACc623886a49c089d9c967ad2c084e03b3";
	   private final static String AUTH_ID = "68df987c25226884c27d5a26def9e0aa";

	 
	
	
	@Override
	public Feedback createFeedback(Feedback fb, int meeting) {
		Twilio.init(ACCOUNT_SID, AUTH_ID);
		Meeting m=meetingrepository.findById(meeting).get();
		
		
		fb.setMeeting(m);

		Message.creator(new PhoneNumber("+21650082707"), new PhoneNumber("+14435012866"),
				   "Message from Spring Boot Application").create();
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
