package tn.esprit.pi.services;

import java.util.List;

import tn.esprit.pi.entities.Response;

public interface IResponseService {

	
	Response QuestionResponse(Response response);
	
	List<Response> getAlResponses();
	void removeResponse(int id);
	Response getResponseById(int id);
	
	List<Response> getResponseByQuestion(int idquestion);

}
