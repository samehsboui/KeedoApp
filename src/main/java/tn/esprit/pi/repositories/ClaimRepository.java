package tn.esprit.pi.repository;



import org.springframework.data.repository.CrudRepository;

import org.springframework.stereotype.Repository;

import tn.esprit.pi.entities.Claim;

@Repository
public interface ClaimRepository extends CrudRepository<Claim,Integer> {

	


}
