package tn.esprit.pi.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import tn.esprit.pi.entities.Role;
import tn.esprit.pi.entities.RoleType;
import tn.esprit.pi.entities.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer>{
	 User findBylogin(String userName);
	    List<User> findBylastName(String userName);
		User findByidUser(int idUser);
		
		@Query("SELECT  u From User u join u.meetings m  WHERE u.role.roleType=:role and  m.id=:meeting  ")

		User findDirector(@Param("role") RoleType role,@Param("meeting") int meeting);

}
