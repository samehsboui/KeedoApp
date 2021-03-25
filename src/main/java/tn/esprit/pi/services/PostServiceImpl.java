package tn.esprit.pi.services;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import com.amazonaws.services.rekognition.AmazonRekognition;
import com.amazonaws.services.rekognition.AmazonRekognitionClientBuilder;
import com.amazonaws.services.rekognition.model.DetectModerationLabelsRequest;
import com.amazonaws.services.rekognition.model.DetectModerationLabelsResult;
import com.amazonaws.services.rekognition.model.Image;
import com.amazonaws.services.rekognition.model.ModerationLabel;
import com.amazonaws.util.IOUtils;
import tn.esprit.pi.entities.Post;
import tn.esprit.pi.entities.Report;
import tn.esprit.pi.entities.ReportPK;
import tn.esprit.pi.entities.User;
import tn.esprit.pi.entities.UnhealthyWord;
import tn.esprit.pi.repositories.FollowRepository;
import tn.esprit.pi.repositories.IPostRepository;
import tn.esprit.pi.repositories.IReportRepository;
import tn.esprit.pi.security.services.UserDetailsImpl;
import tn.esprit.pi.repositories.IUnhealthyWordRepository;



@Service
public class PostServiceImpl implements IPostService{

	
	@Autowired 
	IPostRepository iPostRepository;
	
	@Autowired 
	IReportRepository iReportRepository;
	
	@Autowired 
	private IUnhealthyWordRepository iUnhealthyWordRepository;

	@Autowired 
	private FollowRepository followRepository;
	
	@Override
	public User currentUser() throws Exception{
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return ((UserDetailsImpl) principal).getUser();
	}
	
	
	@Override
	public String addPost(Post p) throws Exception {
		boolean approved=true;
		String msg="";
		p.setUser(currentUser());
		p.setCreateDate(LocalDateTime.now());
		for(UnhealthyWord uwd : iUnhealthyWordRepository.findAll()) {
		//text content approval
			if(p.getPostContent().contains(uwd.getWord())){
				approved=false;
				msg+="Sorry, you can't post hate speech or bad words on Keedo.";
			}
		}
		
		//media (of type image) approval
		if((p.getMedia()).toString().equals("Image") && detect(p.getMediaLink()).isEmpty()==false){
			approved=false;
			msg+=" The image you're trying to upload is against our community standards. Our system detected: "+
					detect(p.getMediaLink())+" labels in this image.";
		}
		
		if(approved==false){
			return (msg);
		}
		else{
			iPostRepository.save(p);
			return ("post added successfully");
		}
	}
	
	@Override
	public String updatePost(Post p, int id) throws Exception {
		boolean approved=true;
		String msg="";		
		int iduser = currentUser().getIdUser();
		String roleuser = currentUser().getRole().getRoleType().name();
		Post post = iPostRepository.findById(id).get();
		//only the owner of the post or the admin can update it
		if (!((iduser==post.getUser().getIdUser())) && !(roleuser=="Admin")){
			return("You are not allowed to update this post");}
		else{
			for(UnhealthyWord uwd : iUnhealthyWordRepository.findAll()) {
				if(p.getPostContent().contains(uwd.getWord())){
					approved=false;
					msg+="Sorry, you can't post hate speech or bad words on Keedo.";
				}
			}
			if((p.getMedia()).toString().equals("Image") && detect(p.getMediaLink()).isEmpty()==false){
				approved=false;
				msg+=" The image you're trying to upload is against our community standards. Our system detected: "+
						detect(p.getMediaLink())+" labels in this image.";
			}
			if(approved==false){
				return (msg);
			}
			else{
				post.setModifyDate(LocalDateTime.now());
				post.setPostContent(p.getPostContent());
				post.setMedia(p.getMedia());
				post.setMediaLink(p.getMediaLink());
				iPostRepository.save(post);
				return ("Post updated successfully");
			}
		}
	}
	
	
	@Override
	public String deletePost(int id) throws Exception {
		int iduser = currentUser().getIdUser();
		String roleuser = currentUser().getRole().getRoleType().name();
		Post p = iPostRepository.findById(id).get();
		if ((iduser==p.getUser().getIdUser()) || roleuser=="Admin"){
			iPostRepository.delete(p);
			return "Post deleted successfully";
		}
		else{
			return "You are not allowed to delete this post";
		}
	}


	@Override
	public List<Post> getMyPosts() throws Exception {
		return iPostRepository.getPostByUserId(currentUser().getIdUser());
	}
	

	@Override
	public List<Post> getAllPosts() {
		List<Post>Posts = new ArrayList<Post>();
		iPostRepository.findAll().forEach(e ->Posts.add(e));
		return Posts;
	}

	
	@Override
	public List<Post> getFollowingPosts() throws Exception {
		List<User> followings = followRepository.listFollowing(currentUser());
		List<Post>Posts = new ArrayList<Post>();
		for(Post p: iPostRepository.findAll()){
			if (followings.contains(p.getUser())){
			Posts.add(p);
		}}
		return Posts;
	}
	
	@Override
	public Post getPostById(int id) {
		return iPostRepository.findById(id).get();  

	}
	
	
	@Override
	public List<Post> getPostsByUserId(int id) {
		return iPostRepository.getPostByUserId(id);
	}
	
	
	@Override
    public List<Post> searchPosts(String pattern){
        return iPostRepository.findPostsByTextContaining(pattern);
    }
	
	
	@Override
	public String sharePost(int idP) throws Exception {
		System.out.println("did it even enter here?");
		Post post=iPostRepository.findById(idP).get();
		System.out.println("this is the current"+currentUser().getIdUser());
		Post newp = new Post();
		newp.setUser(currentUser());
		newp.setPostContent(post.getPostContent());
		newp.setMedia(post.getMedia());
		newp.setMediaLink(post.getMediaLink());
		newp.setOwner(post.getUser().getIdUser());
		newp.setCreateDate(LocalDateTime.now());
		iPostRepository.save(newp);
		return ("post shared successfully");
	}
	
	
	@Override
	public String reportPost(int idP) throws Exception {
		//it doesn't need to check if the report exists cause the post can be updated
		Report r = new Report();
		ReportPK reportPK= new ReportPK();
		reportPK.setIdPost(idP);
		reportPK.setIdUser(currentUser().getIdUser());
		r.setReportPK(reportPK);
		r.setReportDate(LocalDateTime.now());
		iReportRepository.save(r);
		return ("Post reported successfully");
		
	}
	
	
	@Override
	public List<Post> getReportedPosts() {
		return iPostRepository.getReportedPosts();
	}	
	
	
	@Override
	public void approveReportedPost(int idP){
		iReportRepository.deleteReport(idP);
	}
	
	
	@Override
	public int CountPosts() {
		List <Post> posts=(List<Post>) iPostRepository.findAll();
		return posts.size();
	}
	
	
	@Override
	public int CountPostsByUser(int id) {
		List <Post> posts=(List<Post>) iPostRepository.getPostByUserId(id);
		return posts.size();
	}	
	
	
	@Override
	public List<String> detect(String photo) throws Exception {
        ByteBuffer imageBytes;
        try (InputStream inputStream = new FileInputStream(new File(photo))) {
            imageBytes = ByteBuffer.wrap(IOUtils.toByteArray(inputStream));
        }


        AmazonRekognition rekognitionClient = AmazonRekognitionClientBuilder.defaultClient();

        DetectModerationLabelsRequest request = new DetectModerationLabelsRequest()
                .withImage(new Image().withBytes(imageBytes))
                .withMinConfidence(60F);

            DetectModerationLabelsResult result = rekognitionClient.detectModerationLabels(request);
            List<ModerationLabel> labels = result.getModerationLabels();
            List<String> forbidden = new ArrayList<String>() ; 
            System.out.println("Detected labels for " + photo);
            for (ModerationLabel label : labels)
            {
            forbidden.add(label.getName());
               System.out.println("Label: " + label.getName()
                + "\n Confidence: " + label.getConfidence().toString() + "%"
                + "\n Parent:" + label.getParentName());
           
           }
            System.out.println("forbidden labels detected: "+forbidden);
            return (forbidden);
           }


	
/******************may be useful at some point**********************/	
	@Override
	public List<Post> getPostsCommentedByUser(int id) {
		return iPostRepository.getPostsCommentedByUser(id);
	}
	
	@Override
	public List<Post> getPostsLikedByUser(int id) {
		return iPostRepository.getPostsLikedByUser(id);
	}
	
	@Override
	public boolean isReportExists(int idu, int idp) {
	 int count =iReportRepository.isReportExists(idu, idp);
	 if (count==0){
		return false;
	}
	 else {
		 return true;
	 }
	 }

}