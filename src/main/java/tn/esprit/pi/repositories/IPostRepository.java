package tn.esprit.pi.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tn.esprit.pi.entities.Post;

@Repository
public interface IPostRepository  extends CrudRepository<Post,Integer >{
	
	@Query("SELECT p FROM Post p WHERE p.postContent LIKE %?1%")
	List<Post> findPostsByTextContaining(String pattern);
	
	@Query("SELECT p FROM Post p WHERE p.user.id =:id")
	public List<Post> getPostByUserId(@Param("id")int id);
	
}
