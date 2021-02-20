package tn.esprit.pi.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.pi.entities.Event;

@Repository
public interface IEventRepository  extends CrudRepository<Event,Integer >{

}
