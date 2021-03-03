package tn.esprit.pi.services;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.pi.entities.Post;
import tn.esprit.pi.entities.User;
import tn.esprit.pi.repositories.IPostRepository;
import tn.esprit.pi.repositories.IUserRepository;
import tn.esprit.pi.repositories.ICommentRepository;


@Service
public class PostServiceImpl implements IPostService{

	
	@Autowired 
	IPostRepository IPostRepository;
	
	@Autowired 
	private IUserRepository iUserRepository;
	
	@Autowired 
	private ICommentRepository ICommentRepository;
	
	@Override
	public Post addPost(Post p, int idU) {
		User user=iUserRepository.findById(idU).get();
		p.setUser(user);
	    LocalDateTime creationDate = LocalDateTime.now();
		p.setCreateDate(creationDate);
		IPostRepository.save(p);
		return p;
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
}

