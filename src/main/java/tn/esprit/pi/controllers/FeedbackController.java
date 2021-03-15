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
	private String  createFeedback(@RequestBody Feedback fb,@PathVariable("meeting") int meeting)   
	{  
		feedbackservice.createFeedback(fb,meeting);
		return "The Feedback Of "+fb.getMeeting().getTypeMeeting()+" Meeting Passed On "+fb.getMeeting().getDate()+" At :"+fb.getMeeting().getTime()+" was sucessfully Created!";
	}  
	
	@GetMapping("/retrieve-all-feedbacks")
	 @ResponseBody

	 public String getfeedbacks() {
	 List<Feedback> list = feedbackservice.getAllFeedbacks();
	return "Feedback List : \n"+list;
	}
	
	
	@GetMapping("/Feedbacks/retrieve-feedback-details/{idFeedback}")
	 @ResponseBody
	 

	 public String getFeedback(@PathVariable("idFeedback") int idFeedback) {

	
		return "Feedback Details: \n"+feedbackservice.getFeedbackById(idFeedback);
	}
	
	@DeleteMapping("/Feedbacks/delete-feedback/{idFeedback}")  
	 @ResponseBody
	private String RemoveFeedback(@PathVariable("idFeedback") int idFeedback)   
	{  
		
		
		feedbackservice.removeFeedback(idFeedback); 
		return "The Feedback Of Meeting Passed On"+feedbackservice.getFeedbackById(idFeedback).getMeeting().getDate()+" At :"+feedbackservice.getFeedbackById(idFeedback).getMeeting().getTime()+" Was successfully Removed!";
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
