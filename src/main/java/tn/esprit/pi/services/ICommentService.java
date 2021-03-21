package tn.esprit.pi.services;
import java.util.List;
import tn.esprit.pi.entities.Comment;
import tn.esprit.pi.entities.Post;
public interface ICommentService {
	
	public Comment addComment(Comment c, int idU, int idP);
	public void deleteComment(int id);
	public Comment updateComment(Comment c, int id);
	public List<Comment> getAllComments();
	public Comment getCommentById(int id);
	public int CountComments();
	public List<Comment> getCommentsByUserId(int id);
	public int CountCommentsByUser(int id);
	public List<Comment> getCommentsByPostId(int id);
	public int CountCommentsByPost(int id);
    public List<Comment> searchComments(String text);
}