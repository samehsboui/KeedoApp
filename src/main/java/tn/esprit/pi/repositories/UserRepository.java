package tn.esprit.pi.repositories;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



import tn.esprit.pi.entities.User;


@Repository
public interface UserRepository  extends JpaRepository<User, Integer>{
	 User findBylogin(String userName);
	    List<User> findBylastName(String userName);
		User findByidUser(int idUser);
		
		@Query("SELECT r.roleType FROM User u INNER JOIN Role r on (u.role.id = r.id) where  u.id =:id")
		public String getUserRoleDescription(@Param("id")int id);
		
		@Query("SELECT CONCAT(u.firstName,CONCAT(' ',u.lastName)) FROM User u where  u.valid =TRUE")
		public List<String> getUsersFromActivated();
		
		@Query("SELECT CONCAT(u.firstName,CONCAT(' ',u.lastName)) FROM User u where  u.valid =FALSE")
		public List<String> getUsersFromDisabled();

}
