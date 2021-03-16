package tn.esprit.pi.repositories;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.esprit.pi.entities.User;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
	@Query("SELECT new User(u.idUser, u.firstName, u.lastName) FROM User u where u.idUser= :id ")
	User findForChatById(@Param("id") int id);
}
