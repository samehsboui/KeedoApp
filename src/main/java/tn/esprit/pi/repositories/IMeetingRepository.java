package tn.esprit.pi.repositories;

import org.springframework.data.repository.CrudRepository;

import tn.esprit.pi.entities.Jackpot;
import tn.esprit.pi.entities.Meeting;

	public interface IMeetingRepository extends CrudRepository<Meeting,Integer>{

}
