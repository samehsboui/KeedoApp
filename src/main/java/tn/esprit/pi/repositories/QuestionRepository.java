package tn.esprit.pi.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.pi.entities.Feedback;
import tn.esprit.pi.entities.Question;

@Repository
public interface QuestionRepository extends JpaRepository<Question,Integer>{

	List<Question> findByFeedback(Feedback f);
	
	

}
