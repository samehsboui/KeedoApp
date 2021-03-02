package tn.esprit.pi.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.esprit.pi.entities.Feedback;
import tn.esprit.pi.entities.Question;

@Repository
public interface QuestionRepository extends JpaRepository<Question,Integer> {
	
	
/*	
	@Query("SELECT f FROM Feedback f WHERE f.feedback.idFeedback =:idfeedback")
	public List<Question> findByFeedbackId(@Param("idfeedback") int idfeedback);
	*/
	
	public List<Question> findByFeedback(int i );


}
