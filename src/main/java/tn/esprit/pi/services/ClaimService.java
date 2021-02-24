package tn.esprit.pi.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.pi.entities.Claim;
import tn.esprit.pi.entities.ClaimCategory;
import tn.esprit.pi.repository.ClaimRepository;



@Service
public class ClaimService implements IClaimService {

	
	
	@Autowired
	ClaimRepository cr;

	@Override
	public Claim addClaim(Claim c) {
		// TODO Auto-generated method stub
		return cr.save(c) ;
	}

	@Override
	public List<Claim> retrieveAllclaims() {
		// TODO Auto-generated method stub
		
		List <Claim> claims=(List<Claim>) cr.findAll();

		return claims;
	}

	@Override
	public void deleteClaim(int id) {
		// TODO Auto-generated method stub
		Claim c=cr.findById(id).get();
		cr.delete(c);
	}

	@Override
	public Claim updateClaim(Claim c, int id) {

		Claim claim=cr.findById(id).get();
		claim.setCategory(c.getCategory());
		claim.setDescription(c.getDescription());
		claim.setKindergarden(c.getKindergarden());
		claim.setUser(c.getUser());
		return cr.save(claim);
	}

	@Override
	public Claim getClaimById(int id) {
		// TODO Auto-generated method stub
		return cr.findById(id).get();
	}

	@Override
	public int CountClaim() {
		// TODO Auto-generated method stub
		List <Claim> claims=(List<Claim>) cr.findAll();
		return claims.size();
	}

	@Override
	public List<Claim> getClaimByCategory(ClaimCategory cc) {
		// TODO Auto-generated method stub
		return cr.getClaimByCategory(cc);
	}

	@Override
	public List<Claim> getClaimByKindergarden(String k) {
		// TODO Auto-generated method stub
		return cr.getClaimByKindergarden(k);
	}

	
	@Override
	public int CountClaimByKindergarden(String k) {
		// TODO Auto-generated method stub
		
		List <Claim> claims=(List<Claim>) cr.getClaimByKindergarden(k);
		return claims.size();
		
	}
	
	

	

	






}
