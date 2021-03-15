package tn.esprit.pi.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.pi.entities.Claim;
import tn.esprit.pi.entities.Kindergarden;
import tn.esprit.pi.services.ClaimService;
import tn.esprit.pi.services.KindergardenService;

@RestController
public class KindergardenController {
	
	@Autowired
	KindergardenService kindergardenService;
	@Autowired
	ClaimService claimService;
	
	
	@PostMapping("/Kindergartens/add-kindergarden/{director}")  
	private int addKindergarten(@RequestBody Kindergarden kindergarden, @PathVariable("director") int director)   
	{  
		kindergardenService.addKindergarden(kindergarden, director);
		return kindergarden.getId();
	}  
	
	@GetMapping("/Kindergartens/retrieve-all-kindergartens")
	 @ResponseBody

	 public List<Kindergarden> getkindergatens() {
	 List<Kindergarden> list = kindergardenService.retrieveAllKindergardens();
	return list;
	}
	
	@GetMapping("/Kindergartens/retrieve-kindergarten-details/{idKindergarten}")
	 @ResponseBody
	 

	 public Kindergarden getKindergarten(@PathVariable("idKindergarten") int idKindergarten) {

	
		return kindergardenService.getKindergardenById(idKindergarten);
	}
	
	@PutMapping("/Kindergartens/update-kindergarten/{idKindergarten}")  
	private  Kindergarden updateKindergarten(@RequestBody  Kindergarden  Kindergarten, @PathVariable("idKindergarten")int idKindergarten)   
	{  
	
		kindergardenService.updateKindergarden(Kindergarten,idKindergarten);  
		return Kindergarten;  
	} 
	
	@GetMapping("/count-kindergartens")
	 @ResponseBody
	 public int getnbkindergartens() {
	
	return kindergardenService.countKindergardens();
	}
	
	@GetMapping("/Kindergartens/kindergarden/{name}")
	 @ResponseBody
	public Kindergarden getKindergartenByName(@PathVariable String name) {
		 
			return kindergardenService.getKindergardenByName(name);
		}
	
	
	@GetMapping("/Kindergartens/kindergarden-review/{name}")
	 @ResponseBody
	public String getKindergartenReview(@PathVariable String name) {
		Kindergarden k=kindergardenService.getKindergardenByName(name);
		if (claimService.CountClaimByKindergarden(k.getName())<=1)
			return k.getName()+" Is the most Recommended Kindergarden .";
		else
			return k.getName()+" Is the Worst Recommended Kindergarden .";
		
		 
		}
	
}
