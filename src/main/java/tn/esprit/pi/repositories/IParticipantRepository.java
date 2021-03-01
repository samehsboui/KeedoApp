package tn.esprit.pi.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.pi.entities.Participation;

@Repository
public interface IParticipantRepository extends CrudRepository<Participation, Integer> {

	
}
