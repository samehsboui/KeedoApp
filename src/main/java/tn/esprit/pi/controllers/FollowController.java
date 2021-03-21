package tn.esprit.pi.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.pi.entities.FollowRequest;
import tn.esprit.pi.entities.followResponse.FollowListResponse;
import tn.esprit.pi.entities.followResponse.FollowResponse;
import tn.esprit.pi.services.FollowService;

@RestController
public class FollowController {

	@Autowired
	FollowService followservice;
	
	
	@PreAuthorize("permitAll()" )
	 @PostMapping("/{userId}")
	    public FollowResponse followUser(@PathVariable(value = "userId") int userId) throws Exception {
	        return followservice.followUser(userId);
	    }
	 
	
	@PreAuthorize("permitAll()" )
	    @GetMapping("/{userId}")
	    public FollowResponse isFollowingUser(@PathVariable(value = "userId") int userId) throws Exception {
	        return followservice.isFollowing(userId);
	    }
	    
	    
	    @PreAuthorize("permitAll()" )
	    @GetMapping("/{userId}/following")
		   
	    public FollowListResponse getUserFollowing(@PathVariable(value = "userId") int userId) {
	        return followservice.getUserFollowing(userId);
	    }
	    
	    
	    @PreAuthorize("permitAll()" )
	    @GetMapping("/{userId}/followers")
	   
	    public FollowListResponse getUserFollowers(@PathVariable(value = "userId") int userId) {
	        return followservice.getUserFollowers(userId);
	    }
	    
	    
	    @PreAuthorize("permitAll()" )
	    @PostMapping("/{followId}/accept")
	    public FollowResponse acceptFollow(@PathVariable(value = "followId") int followRequestId) {
	        return followservice.acceptFollow(followRequestId);
	    }
	    
	    @PreAuthorize("permitAll()" )
	    @PostMapping("/{followId}/decline")
	    public FollowResponse declineFollow(@PathVariable(value = "followId") int followRequestId) {
	        return followservice.declineFollow(followRequestId);
	    }
	    

	    
	    @PreAuthorize("permitAll()" )
	    @GetMapping("/follow-requests")
			 @ResponseBody
			 public List <FollowRequest> getFollowingRequests() throws Exception {
			
	    	List <FollowRequest> requests=followservice.followRequests();
	    	
			return requests;
			}
	    
	    @PreAuthorize("permitAll()" )
	    @GetMapping("/ownfollowers")
		 @ResponseBody
		 public int getnbCrrentUserFollows() throws Exception {
		
		return followservice.CountCurrentUserFollows();
		}
	    
	    
	    @PreAuthorize("permitAll()" )
		@DeleteMapping("/follows/unfollow/{idFollow}")  
		private void Unfollow(@PathVariable("idFollow") int idFollow)   
		{  
			followservice.unfollow(idFollow); 
		}  
	    

	
}
