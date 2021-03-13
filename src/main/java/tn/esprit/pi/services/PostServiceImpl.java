package tn.esprit.pi.services;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.pi.entities.Post;
import tn.esprit.pi.entities.Report;
import tn.esprit.pi.entities.ReportPK;
import tn.esprit.pi.entities.User;
import tn.esprit.pi.entities.UnhealthyWord;
import tn.esprit.pi.repositories.IPostRepository;
import tn.esprit.pi.repositories.IReportRepository;
import tn.esprit.pi.repositories.IUserRepository;
import tn.esprit.pi.repositories.IUnhealthyWordRepository;



@Service
public class PostServiceImpl implements IPostService{

	
	@Autowired 
	IPostRepository IPostRepository;
	
	@Autowired 
	IReportRepository IReportRepository;
	
	@Autowired 
	private IUserRepository iUserRepository;
	

	@Autowired 
	private IUnhealthyWordRepository IUnhealthyWordRepository;
	
	@Override
	public String addPost(Post p, int idU) {
		User user=iUserRepository.findById(idU).get();
		p.setUser(user);
	    LocalDateTime creationDate = LocalDateTime.now();
		p.setCreateDate(creationDate);
		System.out.println("this is the content "+p.getPostContent());
		for(UnhealthyWord uwd : IUnhealthyWordRepository.findAll()) {
		if(p.getPostContent().contains(uwd.getWord())){
			return ("Sorry, you can't post hate speech or bad words on Keedo");
		}}
		IPostRepository.save(p);
		return ("post added successfully");
		}
	

	@Override
	public void deletePost(int id) {
		//IPostRepository.deleteById(id);
		Post p = IPostRepository.findById(id).get();
		IPostRepository.delete(p);
		
	}

	@Override
	public Post updatePost(Post p, int id) {
		Post post = IPostRepository.findById(id).get();
		LocalDateTime modificationDate = LocalDateTime.now();
		post.setModifyDate(modificationDate);
		post.setPostContent(p.getPostContent());
		post.setMedia(p.getMedia());

		IPostRepository.save(post);
		return getPostById(id);
		
	}

	@Override
	public List<Post> getAllPosts() {
		
		List<Post>Posts = new ArrayList<Post>();
		IPostRepository.findAll().forEach(e ->Posts.add(e));
		return Posts;
	}

	@Override
	public Post getPostById(int id) {
		return IPostRepository.findById(id).get();  

	}
	@Override
	public int CountPosts() {
		List <Post> posts=(List<Post>) IPostRepository.findAll();
		return posts.size();
	}
	
	
	@Override
	public List<Post> getPostsByUserId(int id) {
		return IPostRepository.getPostByUserId(id);
	}
	@Override
	public int CountPostsByUser(int id) {
		List <Post> posts=(List<Post>) IPostRepository.getPostByUserId(id);
		return posts.size();
	}

	@Override
    public List<Post> searchPosts(String pattern){
        return IPostRepository.findPostsByTextContaining(pattern);
    }
	
	@Override
	public List<Post> getPostsCommentedByUser(int id) {
		return IPostRepository.getPostsCommentedByUser(id);
	}
	@Override
	public List<Post> getPostsLikedByUser(int id) {
		return IPostRepository.getPostsLikedByUser(id);
	}
	
	@Override
	public String sharePost(int idP, int idU) {
		User user=iUserRepository.findById(idU).get();
		Post post=IPostRepository.findById(idP).get();
		Post newp = new Post();
		newp.setUser(user);
		newp.setPostContent(post.getPostContent());
		newp.setMedia(post.getMedia());
		newp.setOwner(post.getUser().getIdUser());
	    LocalDateTime creationDate = LocalDateTime.now();
		newp.setCreateDate(creationDate);
		IPostRepository.save(newp);
		return ("post shared successfully");
		}
	@Override
	public boolean isReportExists(int idu, int idp) {
	 int count =IReportRepository.isReportExists(idu, idp);
	 if (count==0){
		return false;
	}
	 else {
		 return true;
	 }
	 }
	@Override
	public String reportPost(int idP, int idU) {
		Report r = new Report();
		ReportPK reportPK= new ReportPK();
		reportPK.setIdPost(idP);
		reportPK.setIdUser(idU);
		r.setReportPK(reportPK);
		LocalDateTime creationDate = LocalDateTime.now();
		r.setReportDate(creationDate);
		IReportRepository.save(r);
		return ("post reported successfully");
		}
	//admin
	
	@Override
	public List<Post> getReportedPosts() {
		return IPostRepository.getReportedPosts();
	}
	
	@Override
	public void approveReportedPost(int idP){
		//IReportRepository.deleteByPost(idP);
		IReportRepository.deleteReport(idP);
	}
	
	@Override
	public void disapproveReportedPost(int idP){
		
	}
	
}
