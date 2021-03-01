package tn.esprit.pi.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.pi.entities.Jackpot;

@Repository
public interface IJackPotRepository  extends CrudRepository<Jackpot,Integer>{


	
	
}
