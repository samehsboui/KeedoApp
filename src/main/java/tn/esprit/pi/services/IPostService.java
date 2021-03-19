package tn.esprit.pi.services;
import java.util.List;
import tn.esprit.pi.entities.Post;
public interface IPostService {
	
	public String addPost(Post p, int idU) throws Exception;
	public void deletePost(int id);
	public Post updatePost(Post p, int id);
	public List<Post> getAllPosts();
	public Post getPostById(int id);
	public int CountPosts();
	public List<Post> getPostsByUserId(int id);
	public int CountPostsByUser(int id);
    public List<Post> searchPosts(String text);
	public List<Post> getPostsCommentedByUser(int id);
	public List<Post> getPostsLikedByUser(int id);
	public String sharePost(int idP, int idU);
	public String reportPost(int idP, int idU);
	public boolean isReportExists(int idu, int idp);
	public List<Post> getReportedPosts();
	void approveReportedPost(int idP);
	List<String> detect(String photo) throws Exception;
}