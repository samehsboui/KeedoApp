package tn.esprit.pi.repositories;



import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.esprit.pi.entities.Claim;
import tn.esprit.pi.entities.ClaimCategory;


@Repository
public interface ClaimRepository extends CrudRepository<Claim,Integer> {

	
	@Query("SELECT c FROM Claim c WHERE c.category =:category")
	public List<Claim> getClaimByCategory(@Param("category")ClaimCategory claimCat);
	
	
	
	@Query("SELECT c FROM Claim c WHERE c.kindergarden.name =:name")
	public List<Claim> getClaimByKindergarden(@Param("name")String k);

}
