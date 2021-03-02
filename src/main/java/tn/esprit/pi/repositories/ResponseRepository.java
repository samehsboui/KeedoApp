package tn.esprit.pi.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.esprit.pi.entities.Claim;
import tn.esprit.pi.entities.ClaimCategory;
import tn.esprit.pi.entities.Response;

@Repository
public interface ResponseRepository extends CrudRepository<Response,Integer>{

	
	@Query("SELECT r FROM Response r WHERE r.question.id =:question")
	public List<Response> getResponseByQuestion(@Param("question") int question);
	
	
}
