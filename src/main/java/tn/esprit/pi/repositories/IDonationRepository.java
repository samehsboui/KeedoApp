package tn.esprit.pi.repositories;

import org.springframework.data.repository.CrudRepository;

import tn.esprit.pi.entities.Advertisement;
import tn.esprit.pi.entities.Donation;


public interface IDonationRepository extends CrudRepository<Donation,Integer >{

}
