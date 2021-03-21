package tn.esprit.pi.services;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import tn.esprit.pi.entities.Follow;
import tn.esprit.pi.entities.FollowRequest;
import tn.esprit.pi.entities.User;
import tn.esprit.pi.entities.followResponse.FollowListResponse;
import tn.esprit.pi.entities.followResponse.FollowResponse;
import tn.esprit.pi.repositories.FollowRepository;
import tn.esprit.pi.repositories.FollowRequestRepository;
import tn.esprit.pi.repositories.UserRepository;
import tn.esprit.pi.security.services.UserDetailsImpl;


@Service
public class FollowService implements IFollowService{

	@Autowired
	FollowRepository followRepository;
	
	@Autowired
	FollowRequestRepository followRequestRepository;
	
	
	@Autowired
	UserRepository userRepository;
	
	@Override
	public FollowResponse followUser(int userId) throws Exception {
        User following = userRepository.findById(userId).get();
        
        Object follower = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if (follower instanceof UserDetailsImpl ) {
        if (following.isPrivate()) {
            FollowRequest followRequest = new FollowRequest();

            followRequest.setFollower(((UserDetailsImpl) follower).getUser());
            followRequest.setFollowing(following);

            followRequestRepository.save(followRequest);

            return new FollowResponse(false);
        }
        Follow followObject = new Follow();
        followObject.setFollower(((UserDetailsImpl) follower).getUser());
        followObject.setFollowing(following);
        followRepository.save(followObject);

		}
        return new FollowResponse(true);
	
	}
	
	
	@Override
	public FollowResponse isFollowing(int userId) throws Exception {
		
		 User following = userRepository.getOne(userId);
	
		 Object follower = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

  
	        Optional<Follow> follow = followRepository.findFollowByFollowerAndFollowing(((UserDetailsImpl) follower).getUser().getIdUser(),following.getIdUser());
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
	public int CountCurrentUserFollows() throws Exception {
		// TODO Auto-generated method stub
		 Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		List <Follow> follows=(List<Follow>) followRepository.findUserFollows(((UserDetailsImpl) principal).getUser().getIdUser());
		return follows.size();
	}


	public void unfollow(int idFollow) {
		followRepository.deleteById(idFollow);
		
	}


	public List<FollowRequest> followRequests() throws Exception {
		// TODO Auto-generated method stub
		
		 Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

				
			List<FollowRequest> requests=followRequestRepository.findAllByFollowing(((UserDetailsImpl) principal).getUser());
		
		
		return requests;
			
	}
	
	

	
}
