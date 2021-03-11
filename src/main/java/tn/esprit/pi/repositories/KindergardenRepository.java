package tn.esprit.pi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.pi.entities.Kindergarden;


@Repository
public interface KindergardenRepository  extends JpaRepository<Kindergarden, Integer>{
	
	Kindergarden findById(int k);

}
