package tn.esprit.pi.services;


import java.util.List;

import tn.esprit.pi.entities.Claim;
import tn.esprit.pi.entities.ClaimCategory;





public interface IClaimService {
	Claim addClaim(Claim c,int kindergarden) throws Exception;
	List<Claim> retrieveAllclaims();
	void deleteClaim(int id);
	
	Claim updateClaim(Claim c,int id) throws Exception;
	Claim getClaimById(int id);
	int CountClaim();
	List<Claim> getClaimByCategory(ClaimCategory cc);
	List<Claim> getClaimByKindergarden(String k);
	int CountClaimByKindergarden(String k);

}
