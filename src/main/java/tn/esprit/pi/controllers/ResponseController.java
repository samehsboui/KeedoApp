package tn.esprit.pi.controllers;

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
	private int addResponse(@RequestBody Response response,@PathVariable("question") int question)   
	{  
		responseservice.FeedbackResponseQuestion(response, question);
		return response.getId();
	} 
	
	@GetMapping("/Responses/retrieve-all-responses_of/{user}")
	 @ResponseBody

	 public List<Response> getAllUserResponse(@PathVariable("user") int user) {
		
			Role r=new Role();
			//if (r.getRoleType()==RoleType.Parent)
	 List<Response> list = responseservice.getAllUserResponses(user);
			
	 return list;
	

	}
	
	@GetMapping("/Responses/retrieve-response-of-user/{user}/of-feedback/{question}")
	 @ResponseBody
	 

	 public Response getResponse(@PathVariable("user") int user ,@PathVariable("question") int question) {

	
		return responseservice.userResponseByQuestion( user, question);
	}
	
	
	
	
	@DeleteMapping("/Responses/delete-response/{idResponse}")  
	private void RemoveResponse(@PathVariable("idResponse") int idResponse)   
	{  
		responseservice.removeResponse(idResponse);
	} 


	
}
