package tn.esprit.pi.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.pi.entities.Kid;

@Repository
public interface KidRepository extends CrudRepository<Kid, Integer> {

	@Query("SELECT new Kid(k.idKid, k.firstName, k.lastName, k.birthDate, k.gender, k.address) FROM Kid k ")
	List<Kid> DisplayAll();
	
	@Query("SELECT new Kid(k.idKid, k.firstName, k.lastName, k.birthDate, k.gender, k.address) FROM Kid k ORDER BY k.firstName ASC")
	List<Kid> orderKidsByNameAsc();
	
	@Query("SELECT new Kid(k.idKid, k.firstName, k.lastName, k.birthDate, k.gender, k.address) FROM Kid k ORDER BY k.firstName DESC")
	List<Kid> orderKidsByNameDESC();
}
