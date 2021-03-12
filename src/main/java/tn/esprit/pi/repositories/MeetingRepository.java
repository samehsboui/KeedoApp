package tn.esprit.pi.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.esprit.pi.entities.Meeting;
import tn.esprit.pi.entities.RoleType;
import tn.esprit.pi.entities.User;

@Repository
public interface MeetingRepository extends CrudRepository<Meeting,Integer>{


	

	
	
	
	
}
