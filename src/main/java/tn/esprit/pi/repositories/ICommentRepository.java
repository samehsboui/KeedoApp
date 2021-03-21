package tn.esprit.pi.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tn.esprit.pi.entities.Comment;
import tn.esprit.pi.entities.Post;

@Repository
public interface ICommentRepository  extends CrudRepository<Comment,Integer >{
	@Modifying (clearAutomatically = true)
	@Query (value= "Delete from Comment c where c.id= :id")
	public void deleteById(@Param("id") int id);
	
	@Query("SELECT c FROM Comment c WHERE c.commentContent LIKE %?1%") /*or you can create one search that contains comments, posts, users and */ 
	public List<Comment> findCommentsByTextContaining(String pattern);
	
	@Query("SELECT c FROM Comment c WHERE c.user.id =:id")
	public List<Comment> getCommentsByUserId(@Param("id")int id);
	
	@Query("SELECT c FROM Comment c WHERE c.post.id =:id")
	public List<Comment> getCommentsByPostId(@Param("id")int id);
	
	

	
	
}