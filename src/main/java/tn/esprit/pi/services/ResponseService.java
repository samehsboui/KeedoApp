package tn.esprit.pi.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.pi.entities.Feedback;

import tn.esprit.pi.entities.Question;
import tn.esprit.pi.entities.Response;
import tn.esprit.pi.entities.User;
import tn.esprit.pi.repositories.FeedbackRepository;
import tn.esprit.pi.repositories.MeetingRepository;
import tn.esprit.pi.repositories.QuestionRepository;
import tn.esprit.pi.repositories.ResponseRepository;
import tn.esprit.pi.repositories.UserRepository;

@Service
public class ResponseService implements IResponseService{

	@Autowired 
	ResponseRepository responserepository;
	
	@Autowired 
	FeedbackRepository feedbackrepository;
	@Autowired 
	QuestionRepository questionrepository;
	@Autowired 
	MeetingRepository meetingrepository;
	
	@Autowired 
	UserRepository userrepository;
	


	@Override
	public Response FeedbackResponseQuestion(Response response,int question) {
		// TODO Auto-generated method stub
		
		Question q=questionrepository.findById(question).get();
		response.setQuestion(q);
		return responserepository.save(response);
	}



	@Override
	public void removeResponse(int id) {
		// TODO Auto-generated method stub
		Response response=responserepository.findById(id).get();
		responserepository.delete(response);
	}

	@Override
	public Response getResponseById(int id) {
		// TODO Auto-generated method stub
		return responserepository.findById(id).get();
	}

	@Override
	public List<Response> getResponseByFeedbackQuestion(int question) {
		// TODO Auto-generated method stub
		
		Question q=questionrepository.findById(question).get();
		
		List <Response> responses=(List<Response>) responserepository.findByQuestion(q);

		return responses;
	}

	@Override
	public List<Response> getAllResponses(int meeting) {
		List <Response> responses=(List<Response>) responserepository.findAll();

		return responses;
	}

	@Override
	public List<Response> getAllUserResponses(int user) {
		
		User u=userrepository.findById(user).get();
		List <Response> responses=(List<Response>) responserepository.UserQuestionsResponses(u.getIdUser());

		return responses;
	}
	

	@Override
	public Response userResponseByQuestion( int user ,int question) {
		
		
		Question q=questionrepository.findById(question).get();
		User u=userrepository.findById(user).get();
		
		return responserepository.findbyUserAndQuestion(u.getIdUser(),q.getId());



		
	}
	
	
	


}
