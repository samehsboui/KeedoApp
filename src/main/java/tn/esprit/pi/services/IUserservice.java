package tn.esprit.pi.services;

import java.util.List;
import java.util.Optional;

import tn.esprit.pi.entities.User;

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


}