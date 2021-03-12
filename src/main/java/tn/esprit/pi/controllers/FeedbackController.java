package tn.esprit.pi.controllers;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.pi.entities.Feedback;
import tn.esprit.pi.services.FeedbackService;

@RestController
public class FeedbackController {
	
	@Autowired
	FeedbackService feedbackservice;



	@PostMapping("/Feedbacks/new-feedback-of/{meeting}")  
	private int createFeedback(@RequestBody Feedback fb,@PathVariable("meeting") int meeting)   
	{  
		feedbackservice.createFeedback(fb,meeting);
		return fb.getIdFeedback(); 
	}  
	
	@GetMapping("/retrieve-all-feedbacks")
	 @ResponseBody

	 public List<Feedback> getfeedbacks() {
	 List<Feedback> list = feedbackservice.getAllFeedbacks();
	return list;
	}
	
	
	@GetMapping("/Feedbacks/retrieve-feedback-details/{idFeedback}")
	 @ResponseBody
	 

	 public Feedback getFeedback(@PathVariable("idFeedback") int idFeedback) {

	
		return feedbackservice.getFeedbackById(idFeedback);
	}
	
	@DeleteMapping("/Feedbacks/delete-feedback/{idFeedback}")  
	 @ResponseBody
	private void RemoveFeedback(@PathVariable("idFeedback") int idFeedback)   
	{  
		feedbackservice.removeFeedback(idFeedback); 
	}  
	
	@GetMapping("/count-feedbacks")
	 @ResponseBody
	 public int getnbfeedbacks() {
	
	return feedbackservice.Countfeedbacks();
	}
	
	
	@GetMapping("/chart")
	public String index() {
		return "index";
	}

}
