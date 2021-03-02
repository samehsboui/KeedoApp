package tn.esprit.pi.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import tn.esprit.pi.entities.Feedback;


@Repository
public interface FeedbackRepository extends CrudRepository<Feedback,Integer>{

	
	
	@Query("SELECT f FROM Feedback f WHERE f.meeting.id =:id")
	public List<Feedback> getmeetingFeedbacks(@Param("id")int id);
	
}
