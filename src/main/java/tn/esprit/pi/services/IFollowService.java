package tn.esprit.pi.services;



import tn.esprit.pi.entities.followResponse.FollowListResponse;
import tn.esprit.pi.entities.followResponse.FollowResponse;

public interface IFollowService {
	
	 FollowResponse followUser(int userId, int currentUser);
	    FollowResponse isFollowing(int userId, int currentUser);
	    FollowListResponse  getUserFollowers(int userId);
	    FollowResponse isUserFollowedByCurrentUser(int currentUser, int userId);
	    FollowListResponse getUserFollowing(int userId);
	    FollowResponse acceptFollow(int followRequestId);
	    FollowResponse declineFollow(int followRequestId);
		int CountCurrentUserFollows(int currentuser);
		void unfollow(int id);
	
	    
	    //void followuser(int userid,int current);
	//boolean usFollowing(int userid,int current);
	//List<Follow> getUserFollowers(int userId);
	//boolean isUserFollowedCurrentUser(User current, int userid);
	//List <Follow> getUserFollowing(int userId);
	//boolean acceptFollow(int followid);
	//boolean declineFollow(int followId);

	//List<Follow> retreiveallfollows();
	//void unfollow(int id);
	//Follow getFollowById(int id);
	//Follow addFollow(Follow f);
	//List<Follow> getFollowBySender(User sender);
	//List<Follow> getFollowByReceiver(User receiver);
	
	//void followUser(User u, Follow f,String extremity);
	


}
