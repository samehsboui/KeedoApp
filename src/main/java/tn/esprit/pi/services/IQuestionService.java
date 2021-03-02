package tn.esprit.pi.services;

import java.util.List;


import tn.esprit.pi.entities.Question;

public interface IQuestionService {
	
	
	Question addQuestion(Question question);
	List<Question> getAllQuestions();
	void removeQuestion(int id);
	Question getQuestionById(int id);
	int CountFeedbackQuestions(int idfeedback);
	
	List<Question> getQuestionByFeedback(int idfeedback);

}
