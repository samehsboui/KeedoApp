package tn.esprit.pi.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.esprit.pi.entities.FollowRequest;


@Repository
public interface FollowRequestRepository extends CrudRepository<FollowRequest,Integer> {

	  List<FollowRequest> findAllByFollowing(int userId);

	    @Query("SELECT f FROM FollowRequest f WHERE f.follower.id= :followerId AND f.following.id = :followingId")
	    Optional<FollowRequest> findByFollowerAndFollowing(@Param("followerId") int followerId, @Param("followingId") int followingId);


	
}
