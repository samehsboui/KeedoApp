package tn.esprit.pi.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.pi.entities.Daycare;

@Repository
public interface DaycareRepository extends CrudRepository<Daycare, Integer> {
	@Query( "select d from Daycare d where d.capacity <= d.nbInscrit")
	List<Daycare> displayDaycareSaturated();
	
	@Query( "select d from Daycare d where d.capacity > d.nbInscrit")
	List<Daycare> displayDaycareNonSaturated();
	
	@Query( "select d from Daycare d ORDER BY d.dateBegin ASC")
	List<Daycare> displayDaycareByDate();
}
