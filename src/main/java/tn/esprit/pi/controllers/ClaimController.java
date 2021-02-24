package tn.esprit.pi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.pi.entities.Claim;
import tn.esprit.pi.entities.ClaimCategory;

import tn.esprit.pi.service.ClaimService;





@RestController
public class ClaimController {
	@Autowired
	ClaimService claimService;
	

	
	@PostMapping("/Claims/add-claim")  
	private int addClaim(@RequestBody Claim claim)   
	{  
		claimService.addClaim(claim);  
		return claim.getIdClaim();  
	}  
	
		@GetMapping("/retrieve-all-claims")
		 @ResponseBody
		 public List<Claim> getclaims() {
		 List<Claim> list = claimService.retrieveAllclaims();
		return list;
		}
		
		@GetMapping("/claims/retrieve-claim-details/{idClaim}")
		 @ResponseBody
		 public Claim getclaim(@PathVariable("idClaim") int idClaim) {

		
			return claimService.getClaimById(idClaim);
		}
		
		@DeleteMapping("/claims/delete-claim/{idClaim}")  
		private void deleteClaim(@PathVariable("idClaim") int idClaim)   
		{  
			claimService.deleteClaim(idClaim);  
		}  
		

		
		@PutMapping("/claims/update-claim/{idClaim}")  
		private Claim updateClaim(@RequestBody Claim claim, @PathVariable("idClaim")int idClaim)   
		{  
		
			claimService.updateClaim(claim,idClaim);  
			return claim;  
		} 
		
		
		
		
		@GetMapping("/count-claims")
		 @ResponseBody
		 public int getnbclaims() {
		
		return claimService.CountClaim();
		}
		@GetMapping("/claims/retrieve-claim-category/{claimcategory}")
		public List<Claim> getClaimByCategory(@PathVariable ClaimCategory claimcategory) {
			 List<Claim> claim = claimService.getClaimByCategory(claimcategory);
			return claim;
			}

		
		@GetMapping("/claims/retrieve-claim-kindergarden/{name}")
		public List<Claim> getClaimByKindergarden(@PathVariable String name) {
			 List<Claim> claim = claimService.getClaimByKindergarden(name);
			return claim;
			}
		
		@GetMapping("/claims/kindergarden-Claims-number/{name}")
		 @ResponseBody
		public int CountClaimByKindergarden(@PathVariable String name) {
			 
				return claimService.CountClaimByKindergarden(name);
			}
}
