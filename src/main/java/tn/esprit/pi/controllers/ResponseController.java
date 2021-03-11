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
import tn.esprit.pi.services.ResponseService;

@RestController
public class ResponseController {

	
	@Autowired
	ResponseService responseservice;
	
	@PostMapping("/Responses/new")  
	private int addResponse(@RequestBody Response response)   
	{  
		responseservice.QuestionResponse(response);
		return response.getId();
	} 
	
	
	@GetMapping("/retrieve-all-responses")
	 @ResponseBody

	 public List<Response> getquestions() {
	 List<Response> list = responseservice.getAlResponses();
	 return list;
	}
	
	@GetMapping("/Response/retrieve-response-details/{idResponse}")
	 @ResponseBody
	 

	 public Response getResponse(@PathVariable("idResponse") int idResponse) {

	
		return responseservice.getResponseById(idResponse);
	}
	
	
	@GetMapping("/Responses/retrieve-question-response/{idQuestion}")
	 @ResponseBody
	 

	 public List<Response> getQuestionResponses(@PathVariable("idQuestion") int idQuestion) {

		List<Response> list = responseservice.getResponseByQuestion(idQuestion);
		return list;
	}
	

	@DeleteMapping("/Responses/delete-response/{idResponse}")  
	private void RemoveResponse(@PathVariable("idResponse") int idResponse)   
	{  
		responseservice.removeResponse(idResponse);
	} 
	
}
