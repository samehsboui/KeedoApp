package tn.esprit.pi.services;


import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import tn.esprit.pi.entities.Claim;
import tn.esprit.pi.entities.ClaimCategory;
import tn.esprit.pi.entities.ClaimStatus;
import tn.esprit.pi.entities.Kindergarden;
import tn.esprit.pi.entities.User;
import tn.esprit.pi.repositories.ClaimRepository;
import tn.esprit.pi.repositories.KindergardenRepository;
import tn.esprit.pi.repositories.IUserRepository;
import tn.esprit.pi.security.services.UserDetailsImpl;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;


@Service
public class ClaimService implements IClaimService {

	
	
	@Autowired
	ClaimRepository cr;

	@Autowired
	IUserRepository ur;
	
	
	
	
	@Autowired
	KindergardenRepository kr;
	
	
	 private final static String ACCOUNT_SID = "ACc623886a49c089d9c967ad2c084e03b3";
	   private final static String AUTH_ID = "3563f93eadf67b4335078edd7ce849c1";


	
	@Override
	public Claim addClaim(Claim c , int kindergarden) throws Exception {
		// TODO Auto-generated method stub
		

		
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if (principal instanceof UserDetailsImpl ) {
			Kindergarden k=kr.findById(kindergarden).get();
		
		
			c.setUser(((UserDetailsImpl) principal).getUser());
			c.setCreatedAt(LocalDateTime.now());
			c.setKindergarden(k);
			c.setStatus(ClaimStatus.Processing);
			Twilio.init(ACCOUNT_SID, AUTH_ID);
			Message.creator(new PhoneNumber(k.getDirector().getTelNum()), new PhoneNumber("+14435012866"),
			  "Dear Sir "+k.getDirector().getFirstName()+" "+k.getDirector().getLastName()+", we would like"
			  		+ " to bring your attention to the fact that you have a complaint about your daycare"
			  		+ " claimed by mr / mrs "+((UserDetailsImpl) principal).getUser().getFirstName()+""
			  				+ " "+((UserDetailsImpl) principal).getUser().getLastName()+" please handle this"
			  						+ " complaint as soon as possible otherwise your subscription "
			  						+ "will be blocked ").create();
			
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
	public Claim processClaim(Claim c, int id) {
		// TODO Auto-generated method stub
		Claim claim=cr.findById(id).get();
		Kindergarden k=kr.findById(claim.getKindergarden().getId()).get();
		
		claim.setCategory(claim.getCategory());
		claim.setDescription(claim.getDescription());
		claim.setCreatedAt(claim.getCreatedAt());
		claim.setUpdatedAt(claim.getUpdatedAt());
		claim.setKindergarden(k);
		claim.setUser(claim.getUser());
		
		claim.setCheckedAt(LocalDateTime.now());
		claim.setStatus(c.getStatus());
		
	
		
		return cr.save(claim);
		
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
	
	
	@Override
	public int CountSkipedClaimByKindergarden(String name) {
		// TODO Auto-generated method stub
		
		List <Claim> claims=(List<Claim>) cr.getClaimByKindergardenAndStatus(name,ClaimStatus.Skiped);
		return claims.size();
		
	}
	
	@Override
	public int CountProcessingClaimByKindergarden(String name) {
		// TODO Auto-generated method stub
		
		List <Claim> claims=(List<Claim>) cr.getClaimByKindergardenAndStatus(name,ClaimStatus.Processing);
		return claims.size();
		
	}
	

	@Override
	public void blockSubscription(String kindergarden) {
		// TODO Auto-generated method stub
	Kindergarden k=kr.findByName(kindergarden);
	
	
	User director=ur.findByidUser(k.getDirector().getIdUser());
		int nb=CountSkipedClaimByKindergarden( k.getName());
		
		if (nb>=4){
			
			director.setBlocked(true);
			ur.save(director);
			}


		
	}




}
