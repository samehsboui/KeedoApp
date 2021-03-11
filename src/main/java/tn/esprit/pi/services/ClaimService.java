package tn.esprit.pi.services;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import tn.esprit.pi.entities.Claim;
import tn.esprit.pi.entities.ClaimCategory;
import tn.esprit.pi.entities.Kindergarden;
import tn.esprit.pi.entities.User;
import tn.esprit.pi.repositories.ClaimRepository;
import tn.esprit.pi.repositories.KindergardenRepository;
import tn.esprit.pi.repositories.UserRepository;



@Service
public class ClaimService implements IClaimService {

	
	
	@Autowired
	ClaimRepository cr;

	@Autowired
	UserRepository ur;
	@Autowired
	KindergardenRepository kr;
	

	
	@Override
	public Claim addClaim(Claim c , int user, int kindergarden) {
		// TODO Auto-generated method stub
		
		User u=ur.findByidUser(user);
		
		
		
		Kindergarden k=kr.findById(kindergarden);
		
			c.setUser(u);
			c.setKindergarden(k);
			/*SimpleMailMessage msg = new SimpleMailMessage();
			msg.setTo("pfe1sem@gmail.com");
			
			msg.setSubject("Testing from Spring Boot");
			msg.setText("Hello World \n Spring Boot Email");
			
			javaMailSender.send(msg);*/
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

		User u=ur.findByidUser(c.getUser().getIdUser());
		Kindergarden k=kr.findById(c.getKindergarden().getId());
		
		
		Claim claim=cr.findById(id).get();
		claim.setCategory(c.getCategory());
		claim.setDescription(c.getDescription());
		claim.setKindergarden(k);
		claim.setUser(u);
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
