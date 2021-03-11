package tn.esprit.pi.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.pi.entities.Question;
import tn.esprit.pi.repositories.QuestionRepository;

@Service
public class QuestionService implements IQuestionService{

	
	@Autowired
	QuestionRepository questionrepository;
	

	@Override
	public Question addQuestion(Question question) {
		// TODO Auto-generated method stub
		return questionrepository.save(question);
	}

	@Override
	public List<Question> getAllQuestions() {
		// TODO Auto-generated method stub
		List <Question> questions=(List<Question>) questionrepository.findAll();

		return questions;
	}

	@Override
	public void removeQuestion(int id) {
		// TODO Auto-generated method stub
		Question question=questionrepository.findById(id).get();
		
		questionrepository.delete(question);
	}

	@Override
	public Question getQuestionById(int id) {
		// TODO Auto-generated method stub
		
		Question question=questionrepository.findById(id).get();

		return question;
	}

	@Override
	public int CountFeedbackQuestions(int idfeedback) {
		// TODO Auto-generated method stub
	
		List <Question> questions=(List<Question>) questionrepository.findByFeedback(idfeedback);

		return questions.size();
	
	}

	@Override
	public List<Question> getQuestionByFeedback(int idfeedback) {
		
		List <Question> questions=(List<Question>) questionrepository.findByFeedback(idfeedback);

		return questions;
	}

}
