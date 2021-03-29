package tn.esprit.pi.repositories;



import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import tn.esprit.pi.entities.Jackpot;
import tn.esprit.pi.entities.Meeting;
import tn.esprit.pi.entities.User;

	public interface IMeetingRepository extends CrudRepository<Meeting,Integer>{
		@Query("SELECT m FROM Meeting m WHERE m.users.idUser =:userId and m.canceler.idUser =:userId and m.canceledAt >= :date ")
		List<Meeting>findParentIdCanceledAfterDate(@Param("userId")int userId,@Param("date")LocalDateTime date);
		
		
		@Query("SELECT m.users FROM   Meeting m  WHERE m.users.idUser =:userId")
	   	User findParentMeeting(@Param("userId")int userId);
		
		

		@Query("SELECT m.users FROM   Meeting m  WHERE m.users.idUser =:userId")
	   	User findKindergardenDirectorMeeting(@Param("userId")int userId);

		

		

		User findByUsers(User idUser);
		
		@Query("SELECT m FROM Meeting m WHERE m.id =:id ")
		Meeting findMeeting(@Param("id") int id);

}
