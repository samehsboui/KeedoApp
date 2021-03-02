package tn.esprit.pi.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.pi.entities.Response;
import tn.esprit.pi.repositories.ResponseRepository;

@Service
public class ResponseService implements IResponseService{

	@Autowired 
	ResponseRepository responserepository;
	
	
	@Override
	public Response QuestionResponse(Response response) {
		// TODO Auto-generated method stub
		
		return responserepository.save(response);
	}

	@Override
	public List<Response> getAlResponses() {
		// TODO Auto-generated method stub
		List <Response> responses=(List<Response>) responserepository.findAll();

		return responses;
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
	public List<Response> getResponseByQuestion(int idquestion) {
		// TODO Auto-generated method stub
		List <Response> responses=(List<Response>) responserepository.getResponseByQuestion(idquestion);

		return responses;
		
	}

}
