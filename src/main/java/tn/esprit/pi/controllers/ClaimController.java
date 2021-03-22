package tn.esprit.pi.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
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
import tn.esprit.pi.security.services.UserDetailsImpl;
import tn.esprit.pi.services.ClaimService;

@RestController
public class ClaimController {
	@Autowired
	ClaimService claimService;
	
	@PreAuthorize("hasAuthority('Parent')" )

	@PostMapping("/Claims/add-claim/{kindergarten}")  
	public int addClaim(@RequestBody Claim claim,@PathVariable("kindergarten") int kindergarten) throws Exception   
	{  
		claimService.addClaim(claim,kindergarten);  
		return claim.getIdClaim();  
	}  
	
	
	@PreAuthorize("hasAuthority('Admin')" )

		@GetMapping("/retrieve-all-claims")
		 @ResponseBody
	
		 public List<Claim> getclaims() {
		 List<Claim> list = claimService.retrieveAllclaims();
		return list;
		}
	
	
	@PreAuthorize("hasAuthority('Admin')" )

		@GetMapping("/claims/retrieve-claim-details/{idClaim}")
		 @ResponseBody
		 

		 public Claim getclaim(@PathVariable("idClaim") int idClaim) {

		
			return claimService.getClaimById(idClaim);
		}
		
	
	@PreAuthorize("hasAuthority('Admin')" )

		@DeleteMapping("/claims/delete-claim/{idClaim}")  
		private void deleteClaim(@PathVariable("idClaim") int idClaim)   
		{  
			claimService.deleteClaim(idClaim);  
		}  
		

	@PreAuthorize("hasAuthority('Parent')" )

		@PutMapping("/claims/update-claim/{idClaim}")  
		public String updateClaim(@RequestBody Claim claim, @PathVariable("idClaim")int idClaim) throws Exception   
		{  
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			claimService.updateClaim(claim,idClaim);  
			return "The claim  was successfuly updated by "+((UserDetailsImpl)principal).getUsername();  
		} 
		
		
		
	@PreAuthorize("hasAuthority('Admin')" )

		@GetMapping("/count-claims")
		 @ResponseBody
		 public int getnbclaims() {
		
		return claimService.CountClaim();
		}
	
	
	@PreAuthorize("hasAuthority('Admin')" )

		@GetMapping("/claims/retrieve-claim-category/{claimcategory}")
		public List<Claim> getClaimByCategory(@PathVariable ClaimCategory claimcategory) {
			 List<Claim> claim = claimService.getClaimByCategory(claimcategory);
			return claim;
			}

	@PreAuthorize("hasAuthority('Admin')" )

		@GetMapping("/claims/retrieve-claim-kindergarden/{name}")
		public List<Claim> getClaimByKindergarden(@PathVariable String name) {
		
		
			 List<Claim> claim = claimService.getClaimByKindergarden(name);
			return claim;
			}
		
	
	@PreAuthorize("hasAuthority('Admin')" )

		@GetMapping("/claims/kindergarden-Claims-number/{name}")
		 @ResponseBody
		public int CountClaimByKindergarden(@PathVariable String name) {
			 
				return claimService.CountClaimByKindergarden(name);
			}
		
		
		
		
}
