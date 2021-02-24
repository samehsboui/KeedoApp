package tn.esprit.pi.repositories;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.pi.entities.Participation;


@Repository
public interface IParticipationRepository extends CrudRepository<Participation,Integer >{

}
