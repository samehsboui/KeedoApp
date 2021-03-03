package tn.esprit.pi.services;
import java.util.List;
import tn.esprit.pi.entities.Post;
public interface IPostService {
	
	public Post addPost(Post p, int idU);
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

}