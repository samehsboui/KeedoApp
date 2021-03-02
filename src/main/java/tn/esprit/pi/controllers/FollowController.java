package tn.esprit.pi.controllers;






import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


import tn.esprit.pi.entities.followResponse.FollowListResponse;
import tn.esprit.pi.entities.followResponse.FollowResponse;
import tn.esprit.pi.services.FollowService;

@RestController
public class FollowController {

	@Autowired
	FollowService followservice;

	 @PostMapping("/{userId}/{currentuser}")
	    public FollowResponse followUser(@PathVariable(value = "userId") int userId,
	    		@PathVariable(value = "currentuser")int currentUser) {
	        return followservice.followUser(userId, currentUser);
	    }
	 
	    @GetMapping("/{userId}/{currentuser}")
	    public FollowResponse isUserFollowingAnotherUser(@PathVariable(value = "userId") int userId,
	    		@PathVariable(value = "currentuser")int currentUser) {
	        return followservice.isFollowing(userId, currentUser);
	    }
	    
	    @GetMapping("/{userId}/following")
		   
	    public FollowListResponse getUserFollowing(@PathVariable(value = "userId") int userId) {
	        return followservice.getUserFollowing(userId);
	    }
	    
	    @GetMapping("/{userId}/followers")
	   
	    public FollowListResponse getUserFollowers(@PathVariable(value = "userId") int userId) {
	        return followservice.getUserFollowers(userId);
	    }
	    
	    @PostMapping("/{followId}/accept")
	    public FollowResponse acceptFollow(@PathVariable(value = "followId") int followRequestId) {
	        return followservice.acceptFollow(followRequestId);
	    }
	    
	    
	    @PostMapping("/{followId}/decline")
	    public FollowResponse declineFollow(@PathVariable(value = "followId") int followRequestId) {
	        return followservice.declineFollow(followRequestId);
	    }
	    
	    
	    @GetMapping("/{userId}/follow")
	    public FollowResponse isUserFollowedByCurrentUser(@PathVariable(value = "currentuser")int currentUser,
	                                                      @PathVariable(value = "userId") int userId) {
	        return followservice.isUserFollowedByCurrentUser(currentUser, userId);
	    }
	    
	    
	    @GetMapping("/{currentuser}/ownfollows")
		 @ResponseBody
		 public int getnbCrrentUserFollows(@PathVariable(value = "currentuser")int currentUser) {
		
		return followservice.CountCurrentUserFollows(currentUser);
		}
	    
		@DeleteMapping("/follows/unfollow/{idFollow}")  
		private void Unfollow(@PathVariable("idFollow") int idFollow)   
		{  
			followservice.unfollow(idFollow); 
		}  
	    
	/*
	 @PostMapping("/{userId}/{current}")

	    public void followUser(@PathVariable(value = "userId") int userid,@PathVariable(value = "current")int current) {
	         followservice.followuser(userid, current);
	    } 
	 
	   @GetMapping("isfollowing/{userId}/{current}")
	    public boolean isUserFollowingAnotherUser(@PathVariable(value = "userId") int userid,@PathVariable(value = "current")int current) {
	        return followservice.usFollowing(userid, current);
	    }
	   
	   
	   @GetMapping("/{userId}/followers")
	    public List<Follow> getUserFollowers(@PathVariable(value = "userId") int userId) {
	        List<Follow> list =followservice.getUserFollowers(userId);
	        return list;
	    }
	   
	   
	   @GetMapping("/{userId}/followers-number")
	    public int CountUserFollowers(@PathVariable(value = "userId") int userId) {
	        List<Follow> list =followservice.getUserFollowers(userId);
	        return list.size();
	    }
	   */
	/*
	@GetMapping("/retrieve-all-follows")
	 @ResponseBody
	 public List<Follow> getfollows() {
	 List<Follow> list = followservice.retreiveallfollows();
	return list;
	}
	
	
	@GetMapping("/follow/retrieve-follow-details/{idFollow}")
	 @ResponseBody
	 public Follow getFollow(@PathVariable("idFollow") int idFollow) {

	
		return followservice.getFollowById(idFollow);
	}
	
	@DeleteMapping("/follows/unfollow/{idFollow}")  
	private void Unfollow(@PathVariable("idFollow") int idFollow)   
	{  
		followservice.unfollow(idFollow); 
	}  
	*/
	
}
