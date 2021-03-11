package tn.esprit.pi.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.pi.entities.Meeting;

@Repository
public interface MeetingRepository extends CrudRepository<Meeting,Integer>{

}
