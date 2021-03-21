package tn.esprit.pi.services;


import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import tn.esprit.pi.entities.Claim;
import tn.esprit.pi.entities.ClaimCategory;
import tn.esprit.pi.entities.Kindergarden;
import tn.esprit.pi.entities.User;
import tn.esprit.pi.repositories.ClaimRepository;
import tn.esprit.pi.repositories.KindergardenRepository;
import tn.esprit.pi.repositories.UserRepository;
import tn.esprit.pi.security.services.UserDetailsImpl;



@Service
public class ClaimService implements IClaimService {

	
	
	@Autowired
	ClaimRepository cr;

	@Autowired
	UserRepository ur;
	@Autowired
	KindergardenRepository kr;
	

	
	@Override
	public Claim addClaim(Claim c , int kindergarden) throws Exception {
		// TODO Auto-generated method stub
		
		//User u=ur.findByidUser(user);
		
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if (principal instanceof UserDetailsImpl ) {
			Kindergarden k=kr.findById(kindergarden).get();
		
		
			c.setUser(((UserDetailsImpl) principal).getUser());
			c.setCreatedAt(LocalDateTime.now());
			c.setKindergarden(k);
			/*SimpleMailMessage msg = new SimpleMailMessage();
			msg.setTo("pfe1sem@gmail.com");
			
			msg.setSubject("Testing from Spring Boot");
			msg.setText("Hello World \n Spring Boot Email");
			
			javaMailSender.send(msg);*/
		}
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
	public Claim updateClaim(Claim c, int id) throws Exception {

		Claim claim=cr.findById(id).get();
		Kindergarden k=kr.findById(claim.getKindergarden().getId()).get();


		
		
		
		claim.setCategory(c.getCategory());
		claim.setDescription(c.getDescription());
		claim.setCreatedAt(claim.getCreatedAt());
		claim.setUpdatedAt(LocalDateTime.now());
		claim.setKindergarden(k);
		claim.setUser(claim.getUser());
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
