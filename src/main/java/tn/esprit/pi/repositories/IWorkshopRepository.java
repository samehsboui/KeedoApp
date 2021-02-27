package tn.esprit.pi.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tn.esprit.pi.entities.Workshop;

@Repository
public interface IWorkshopRepository  extends CrudRepository<Workshop,Integer >{
	
	@Query("SELECT w FROM Workshop w WHERE w.content LIKE %?1%")
	List<Workshop> findWorkshopsByTextContaining(String text);
	
	@Query("SELECT w FROM Workshop w WHERE w.user.id =:id")
	public List<Workshop> getWorkshopByUserId(@Param("id")int id);
	
	//needs fixing
	
	@Query("SELECT w FROM Workshop w WHERE w.user.firstName =:name")
	public List<Workshop> getWorkshopByKindergartenName(@Param("name")String name);
	
}