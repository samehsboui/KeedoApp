package tn.esprit.pi.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.pi.entities.Claim;
import tn.esprit.pi.entities.Kindergarden;
import tn.esprit.pi.entities.RoleType;
import tn.esprit.pi.entities.User;
import tn.esprit.pi.security.services.UserDetailsImpl;
import tn.esprit.pi.services.ClaimService;
import tn.esprit.pi.services.KindergardenService;

@RestController
@RequestMapping("/Kindergartens")
public class KindergardenController {
	
	@Autowired
	KindergardenService kindergardenService;
	@Autowired
	ClaimService claimService;

	
	@PreAuthorize("hasAuthority('Admin')")
	@PostMapping("/add-kindergarden/{director}")  
	

	public String addKindergarten(@RequestBody Kindergarden kindergarden,@PathVariable("director") int director)   
	{  
		kindergardenService.addKindergarden(kindergarden,director);
		if (kindergarden.getId()>0)
		return "Kindergaten added successfuly";
		else
			return "We found a problem when  adding the kindergarten";
	}  
	@PreAuthorize("hasAuthority('Admin')" )
	@GetMapping("/retrieve-all-kindergartens")
	 @ResponseBody

	 public List<Kindergarden> getkindergatens() {
	 List<Kindergarden> list = kindergardenService.retrieveAllKindergardens();
	return list;
	}
	
	
	@PreAuthorize("hasAuthority('Parent')" )

	@GetMapping("/retrieve-kindergarten-details/{idKindergarten}")
	 @ResponseBody
	 

	 public Kindergarden getKindergarten(@PathVariable("idKindergarten") int idKindergarten) {

	
		return kindergardenService.getKindergardenById(idKindergarten);
	}
	
	
	@PreAuthorize("hasAuthority('KindergardenDirector')" )
	//@PreAuthorize("permitAll()" )
	@PutMapping("/update-kindergarten/{idKindergarten}")  
	public  String updateKindergarten(@RequestBody  Kindergarden  Kindergarten, @PathVariable("idKindergarten") int idKindergarten)   
	{  
	
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if (principal instanceof UserDetailsImpl && Kindergarten.getDirector()== principal) {

		
		kindergardenService.updateKindergarden(Kindergarten,idKindergarten);  
		return "The kindergarden account was successfuly updated by her director ";  }else{
			return "Sorry "+((UserDetailsImpl)principal).getUsername()+", you don't have the permission to modify the content of this kindergarten account because your are not the responsible to it.   ";	}
	} 
	
	
	@PreAuthorize("hasAuthority('Admin')" )

	@GetMapping("/count-kindergartens")
	 @ResponseBody
	 public int getnbkindergartens() {
	
	return kindergardenService.countKindergardens();
	}
	
	
	@PreAuthorize("permitAll()" )

	@GetMapping("/kindergarden/{name}")
	 @ResponseBody
	public Kindergarden getKindergartenByName(@PathVariable String name) {
		 
			return kindergardenService.getKindergardenByName(name);
		}
	
	
	@PreAuthorize("permitAll()" )
	@GetMapping("/kindergarden-review/{name}")
	 @ResponseBody
	public String getKindergartenReview(@PathVariable String name) {
		
	
		Kindergarden k=kindergardenService.getKindergardenByName(name);
		if (claimService.CountClaimByKindergarden(k.getName())<=1)
			return k.getName()+" Is the most Recommended Kindergarten .";
		else
			return k.getName()+" Is the Worst Recommended Kindergarten .";
		
		 
		}
	
	
}
