package tn.esprit.pi.repositories;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import tn.esprit.pi.entities.Jackpot;
import tn.esprit.pi.entities.Meeting;
import tn.esprit.pi.entities.User;

	public interface IMeetingRepository extends JpaRepository<Meeting,Integer>{

	
		

		User findByUsers(User idUser);
		
		@Query("SELECT m FROM Meeting m WHERE m.id =:id ")
		Meeting findMeeting(@Param("id") int id);


}
