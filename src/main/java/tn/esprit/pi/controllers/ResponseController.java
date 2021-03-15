package tn.esprit.pi.controllers;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.pi.entities.Response;
import tn.esprit.pi.entities.Role;
import tn.esprit.pi.entities.RoleType;
import tn.esprit.pi.entities.User;
import tn.esprit.pi.services.ResponseService;

@RestController
public class ResponseController {

	
	@Autowired
	ResponseService responseservice;
	
	@PostMapping("/Responses/responseTo/{question}")  
	private String addResponse(@RequestBody Response response,@PathVariable("question") int question)   
	{  
		
		response.setCreatedAt(LocalDateTime.now());
		responseservice.FeedbackResponseQuestion(response, question);
	
		return "The answer to question "+question+" of '"+response.getQuestion().getFeedback().getMeeting().getTypeMeeting()+"' Meeting Feedback wich is titled '"+response.getQuestion().getFeedback().getTitle()+"' is well recorded   ";
	} 
	
	
	@GetMapping("/Responses/retrieve-all-responses_of_feedback/{feedback}")
	 @ResponseBody

	 public List<Response> getFeedbackResponses(@PathVariable("feedback") int feedback) throws Exception {
		
	 List<Response> list = responseservice.getResponseByFeedback(feedback);
			
	 return list;
	

	}
	
	
	@GetMapping("/Responses/retrieve-all-responses_of/{user}")
	 @ResponseBody

	 public List<Response> getAllUserResponse(@PathVariable("user") int user) throws Exception {
		
			Role r=new Role();
			//if (r.getRoleType()==RoleType.Parent)
	 List<Response> list = responseservice.getAllUserResponses(user);
			
	 return list;
	

	}
	
	
	
	
	@GetMapping("/Responses/retrieve-response-of-user/{user}/of-question/{question}")
	 @ResponseBody
	 

	 public Response getResponse(@PathVariable("user") int user ,@PathVariable("question") int question) throws Exception {

	
		return responseservice.userResponseByQuestion( user, question);
	}
	
	
	
	
	@DeleteMapping("/Responses/delete-response/{idResponse}")  
	private void RemoveResponse(@PathVariable("idResponse") int idResponse)   
	{  
		responseservice.removeResponse(idResponse);
	} 


	
}
