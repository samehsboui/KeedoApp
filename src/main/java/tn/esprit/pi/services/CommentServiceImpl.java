package tn.esprit.pi.services;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.pi.entities.Comment;
import tn.esprit.pi.entities.Post;
import tn.esprit.pi.entities.User;
import tn.esprit.pi.repositories.ICommentRepository;
import tn.esprit.pi.repositories.IPostRepository;
import tn.esprit.pi.repositories.IUserRepository;


@Service
public class CommentServiceImpl implements ICommentService{
	
	@Autowired 
	IPostRepository IPostRepository;

	@Autowired 
	private IUserRepository IUserRepository;
	
	@Autowired 
	private ICommentRepository ICommentRepository;

	@Override
	public Comment addComment(Comment c, int idU, int idP) {
		User user=IUserRepository.findById(idU).get();
		Post post=IPostRepository.findById(idP).get();
		c.setUser(user);
		c.setPost(post);
	    LocalDateTime creationDate = LocalDateTime.now();
		c.setCreateDate(creationDate);
		ICommentRepository.save(c);
		return c;
	}

	
	@Override
	public void deleteComment(int id) {
		ICommentRepository.deleteById(id);
	}

	@Override
	public Comment updateComment(Comment c, int id) {
		Comment comment = ICommentRepository.findById(id).get();
		LocalDateTime modificationDate = LocalDateTime.now();
		comment.setModifyDate(modificationDate);
		comment.setCommentContent(c.getCommentContent());

		ICommentRepository.save(comment);
		return getCommentById(id);		
	}


	@Override
	public List<Comment> getAllComments() {
		List<Comment>Comments = new ArrayList<Comment>();
		ICommentRepository.findAll().forEach(e ->Comments.add(e));
		return Comments;
	}

	@Override
	public Comment getCommentById(int id) {
		return ICommentRepository.findById(id).get();  
	}

	@Override
	public int CountComments() {
		List <Comment> comments=(List<Comment>) ICommentRepository.findAll();
		return comments.size();
	}

	@Override
	public List<Comment> getCommentsByUserId(int id) {
		return ICommentRepository.getCommentsByUserId(id);

	}

	@Override
	public int CountCommentsByUser(int id) {
		List <Comment> comments=(List<Comment>) ICommentRepository.getCommentsByUserId(id);
		return comments.size();
	}

	@Override
	public List<Comment> getCommentsByPostId(int id) {
		return ICommentRepository.getCommentsByPostId(id);
	}

	@Override
	public int CountCommentsByPost(int id) {
		List <Comment> comments=(List<Comment>) ICommentRepository.getCommentsByPostId(id);
		return comments.size();
	}

	@Override
	public List<Comment> searchComments(String pattern) {
        return ICommentRepository.findCommentsByTextContaining(pattern);
	}


}
