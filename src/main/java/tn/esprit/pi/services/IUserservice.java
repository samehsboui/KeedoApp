package tn.esprit.pi.services;

import java.util.List;

import org.springframework.stereotype.Service;

import tn.esprit.pi.entities.EmpruntBook;
import tn.esprit.pi.entities.User;
@Service
public interface IUserservice {
	public List<User> getAllUsers();
	public User getUserById(int id) throws Exception;
 	public User activateUser (User user) throws Exception;
	public User desactivateUser (User user) throws Exception;
	public User createUser(User entity) throws Exception;
	public User updateUser(User entity) throws Exception;
	public void deleteUserById(Integer userId) throws Exception;
	public User findUserBylogin(String user) throws Exception;
	public List<User> findUserLastName(String user) throws Exception;
	public String getUserRoleDescription(int id);
	public List<String> findUsersActivated() throws Exception;	
	public List<String> getUsersFromDisabled();
	public void increaseFailedAttempts(User user);
	boolean unlockWhenTimeExpired(User user);
	void resetFailedAttempts(String email);
	void lock(User user);
	public User findUserByResetToken(String token);

	//dhekra
	User findById(int id);
	User findBymail(String mail);
	List<User> findAllByOrderByIdAsc();
	public List<EmpruntBook> findEmpruntsByUserFirstName(String D);


}
