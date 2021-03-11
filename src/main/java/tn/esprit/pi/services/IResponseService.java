package tn.esprit.pi.services;

import java.util.List;

import tn.esprit.pi.entities.Response;


public interface IResponseService {


	Response FeedbackResponseQuestion(Response response,int question);
	Response userResponseByQuestion(int user ,int question);
	List<Response> getAllUserResponses(int user);

	List<Response> getAllResponses(int meeting);
	void removeResponse(int id);
	Response getResponseById(int id);
	
	List<Response> getResponseByFeedbackQuestion(int question);


	
	
}
