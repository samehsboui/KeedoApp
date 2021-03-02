package tn.esprit.pi.entities.followResponse;

import java.util.ArrayList;
import java.util.List;

import tn.esprit.pi.entities.User;

public class FollowListResponse {
	  private List<User> userList;

	    public List<User> getUserList() {
	        return userList;
	    }

	    public void setUserList(List<User> userList) {
	        this.userList = userList;
	    }

	    public static FollowListResponse mapUserListToUsersSummaries(List<User> followersList) {
	        FollowListResponse followListResponse = new FollowListResponse();
	        List<User> userSummaries = new ArrayList<>();

	        for (User user : followersList)
	            userSummaries.add(mapUsersToUserSummaries(user));

	        followListResponse.setUserList(userSummaries);

	        return followListResponse;
	    }
	    
	    public static User mapUsersToUserSummaries(User user) {
	    	User userSummary = new User();

	        userSummary.setIdUser(user.getIdUser());
	        userSummary.setFirstName(user.getFirstName());
	 

	        return userSummary;
	    }
}
