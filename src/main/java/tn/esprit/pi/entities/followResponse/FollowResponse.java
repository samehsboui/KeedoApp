package tn.esprit.pi.entities.followResponse;

public class FollowResponse {
	 private boolean followed;

	    public FollowResponse() {
	    }

	    public FollowResponse(boolean followed) {
	        this.followed = followed;
	    }

	    public boolean isFollowed() {
	        return followed;
	    }

	    public void setFollowed(boolean followed) {
	        this.followed = followed;
	    }
}
