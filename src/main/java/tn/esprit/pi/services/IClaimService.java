package tn.esprit.pi.services;


import java.util.List;

import tn.esprit.pi.entities.Claim;
import tn.esprit.pi.entities.ClaimCategory;





public interface IClaimService {
	Claim addClaim(Claim c, int user,int kindergarden);
	List<Claim> retrieveAllclaims();
	void deleteClaim(int id);
	
	Claim updateClaim(Claim c,int id);
	Claim getClaimById(int id);
	int CountClaim();
	List<Claim> getClaimByCategory(ClaimCategory cc);
	List<Claim> getClaimByKindergarden(String k);
	int CountClaimByKindergarden(String k);

}
