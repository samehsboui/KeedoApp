package tn.esprit.pi.services;

import java.util.List;

import tn.esprit.pi.entities.Feedback;

public interface IFeedbackService {

	
	Feedback createFeedback(Feedback fb);
	List<Feedback> getAllFeedbacks();
	void removeFeedback(int id);
	Feedback getFeedbackById(int id);
	int Countfeedbacks();
	
	List<Feedback> getFeedbackByMeeting(int meeting);

	
}
