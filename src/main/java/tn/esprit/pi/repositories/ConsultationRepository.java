package tn.esprit.pi.repositories;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.esprit.pi.entities.Consultation;
import tn.esprit.pi.entities.Kid;

@Repository
public interface ConsultationRepository extends CrudRepository<Consultation, Integer>{

	@Query("select new Consultation(c.dateConsultation, c.time, c.doctorName) from Consultation c")
	List<Consultation> displayAllConsultations();
	@Query(" select c from Consultation c where c.kid = :kid")
	List<Consultation> findConsultationByKid(@Param("kid") Kid kid);
	
	@Transactional
	@Modifying
	@Query("DELETE from Consultation c where c.id= :id")
	void deleteConsultById(@Param("id") int id);
}
