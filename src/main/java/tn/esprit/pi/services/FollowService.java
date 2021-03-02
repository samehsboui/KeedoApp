package tn.esprit.pi.services;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.pi.entities.Follow;
import tn.esprit.pi.entities.FollowRequest;
import tn.esprit.pi.entities.User;
import tn.esprit.pi.entities.followResponse.FollowListResponse;
import tn.esprit.pi.entities.followResponse.FollowResponse;
import tn.esprit.pi.repositories.FollowRepository;
import tn.esprit.pi.repositories.FollowRequestRepository;
import tn.esprit.pi.repositories.UserRepository;


@Service
public class FollowService implements IFollowService{

	@Autowired
	FollowRepository followRepository;
	
	@Autowired
	FollowRequestRepository followRequestRepository;
	
	
	@Autowired
	UserRepository userRepository;
	
	@Override
	public FollowResponse followUser(int userId, int currentUser) {
		User follower = userRepository.findById(currentUser).get();
        User following = userRepository.findById(userId).get();
        
   
        if (following.isPrivate()) {
            FollowRequest followRequest = new FollowRequest();

            followRequest.setFollower(follower);
            followRequest.setFollowing(following);

            followRequestRepository.save(followRequest);

            return new FollowResponse(false);
        }
        Follow followObject = new Follow();
        followObject.setFollower(follower);
        followObject.setFollowing(following);
        followRepository.save(followObject);

    
        return new FollowResponse(true);
	
	}
	
	
	@Override
	public FollowResponse isFollowing(int userId, int currentUser) {
		  User follower = userRepository.findById(currentUser).get();
	        User following = userRepository.getOne(userId);
	        Optional<Follow> follow = followRepository.findFollowByFollowerAndFollowing(follower.getIdUser()
	        		, following.getIdUser());
	        if (follow.isPresent()) {
	            return new FollowResponse(true);
	        }
	        return new FollowResponse(false);
	}
	
	
	
	@Override
	public FollowListResponse getUserFollowers(int userId) {
		 User user = userRepository.findById(userId).get();

	        List<Follow> followerList = followRepository.findAllByFollowing(user.getIdUser());
	        List<User> userList = new ArrayList<>();
	        for (Follow follow : followerList) {
		 

	            userList.add(userRepository.findById(follow.getFollower().getIdUser()).get());
	   
		
	        }
	        return FollowListResponse.mapUserListToUsersSummaries(userList);
	}
	@Override
	public FollowResponse isUserFollowedByCurrentUser(int currentUser, int userId) {
		// TODO Auto-generated method stub
	     return new FollowResponse(false);
	}
	@Override
	public FollowListResponse getUserFollowing(int userId) {
		 User user = userRepository.findById(userId).get();

	        List<Follow> followingList = followRepository.findAllByFollower(user.getIdUser());
	        List<User> userList = new ArrayList<>();
	        for (Follow follow : followingList) {
		      

	            userList.add(userRepository.findById(follow.getFollowing().getIdUser()).get());
	            
		  
	        }
	        return FollowListResponse.mapUserListToUsersSummaries(userList);
	}
	@Override
	public FollowResponse acceptFollow(int followRequestId) {
		 FollowRequest followRequest = followRequestRepository.findById(followRequestId).get();
	        System.out.println(followRequest);
	        if (followRequest != null) {
	            Follow followObject = new Follow();
	            followObject.setFollower(followRequest.getFollower());
	            followObject.setFollowing(followRequest.getFollowing());
	            followRepository.save(followObject);


	          
	            followRequestRepository.delete(followRequest);
	            return new FollowResponse(true);

	        }
	            return new FollowResponse(false);
	}
	@Override
	public FollowResponse declineFollow(int followRequestId) {
		 followRequestRepository.deleteById(followRequestId);
	        return new FollowResponse(false);
	}

	
	@Override
	public int CountCurrentUserFollows(int currentuser) {
		// TODO Auto-generated method stub
		List <Follow> follows=(List<Follow>) followRepository.findUserFollows(currentuser);
		return follows.size();
	}


	public void unfollow(int idFollow) {
		followRepository.deleteById(idFollow);
		
	}
	
	
	
/*
	@Override
	public void followuser(int userid, int current) {
		// TODO Auto-generated method stub
		
		 User follower = userRepository.findById(userid).get();
		 User following = userRepository.findById(current).get();
		 
	  
		 Follow followrequest=new Follow();

		 followrequest.setFollower(follower);;
		 followrequest.setFollowing(following);
		 
		 followRepository.save(followrequest);
		  
	
		 
	}
	@Override
	public boolean usFollowing(int userid, int current) {
		 User follower = userRepository.findById(userid).get();
		 User following = userRepository.findById(current).get();
		 
		 
		 Follow follow =followRepository.findFollowByFollowerAndFollowing(follower, following);
		 if (follow.getFollower().equals(true))
			 return true;

		 else
		 return false;
	}
	@Override
	public List<Follow> getUserFollowers(int userId) {
		
		

		List <Follow> follows=(List<Follow>) followRepository.findUserFollows(userId);

		return follows;
	
	}
	@Override
	public boolean isUserFollowedCurrentUser(User current, int userid) {
		// TODO Auto-generated method stub
		
		
		return false;
	}
	@Override
	public List<Follow> getUserFollowing(int userId) {
		// TODO Auto-generated method stub
		 User user = userRepository.findById(userId).get();

	        List<Follow> followingList = (List<Follow>) followRepository.findFollowByFollowerId(user.getIdUser());
	        List<User> userList = new ArrayList<>();
	        for (Follow follow : followingList) {
	        	
	        	
	        User following=	userRepository.findById(follow.getFollowing().getIdUser()).get();
	            userList.add(following);
	        }
		return null;
	}
	@Override
	public boolean acceptFollow(int followid) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean declineFollow(int followId) {
		// TODO Auto-generated method stub
		return false;
	}
	
	*/
	/*
	@Override
	public List<Follow> retreiveallfollows() {
		// TODO Auto-generated method stub
		
		List <Follow> follows=(List<Follow>) followRepository.findAll();

		return follows;
		
	}

	@Override
	public void unfollow(int id) {
		// TODO Auto-generated method stub
		followRepository.deleteById(id);
	}

	@Override
	public Follow getFollowById(int id) {
		// TODO Auto-generated method stub
		return followRepository.findById(id).get();
	}

	

	

	@Override
	public void followUser(User u, Follow f, String extremity) {
		  if (extremity.equals("SENDER")) {
	            List<Follow> follows = u.getSentfollows();
	            follows.add(f);
	            u.setSentfollows(follows);
	        }
	        else if(extremity.equals("RECEIVER")){
	            List<Follow> follows = u.getReceivefollows();
	            follows.add(f);
	            u.setReceivefollows(follows);
	        }
		  userRepository.save(u);
		
	}


	@Override
	public List<Follow> getFollowBySender(User sender) {
		List <Follow> follows=(List<Follow>) followRepository.getFollowBySender(sender);

		return follows;
	}

	@Override
	public List<Follow> getFollowByReceiver(User receiver) {
		List <Follow> follows=(List<Follow>) followRepository.getFollowByReceiver(receiver);

		return follows;
	}
	
	@Override
	public Follow addFollow(Follow f) {
		// TODO Auto-generated method stub
		return followRepository.save(f);
	}
	
	
	*/
	
	
}
