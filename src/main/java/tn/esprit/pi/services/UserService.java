package tn.esprit.pi.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.pi.entities.*;
import tn.esprit.pi.repositories.IUserRepository;

@Service
public class UserService implements IUserservice {

	@Autowired
	IUserRepository userRepository;

	@Override
	public List<User> getAllUsers() {
		List<User> usereList = userRepository.findAll();

		if (usereList.size() > 0) {
			return usereList;
		} else {
			return new ArrayList<User>();
		}
	}

	@Override
	public User getUserById(int id) throws Exception {
		return userRepository.findByidUser(id);
	}

	@Override
	public User createUser(User entity) throws Exception {
		return userRepository.save(entity);
	}
	
	@Override
	public User updateUser(User entity) throws Exception {
		User user = userRepository.findByidUser(entity.getIdUser());
		if (user!=null) {
			return userRepository.save(entity);
		} else {
			throw new Exception("No the user record exist");
		}
	}

	
	@Override
	public void deleteUserById(Integer userId) throws Exception {
		userRepository.deleteById(userId);
	}
	
	@Override
	public User activateUser (User user) throws Exception {
		user.setValid(true);
		return updateUser(user);
	}
	
	@Override
	public User desactivateUser (User user) throws Exception {
		user.setValid(false);
		return updateUser(user);
	}
	
	@Override
	public User findUserBylogin (String user) throws Exception {
		return userRepository.findBylogin(user);
	}
	
	@Override
	public List<User> findUserLastName (String username) throws Exception {
		return userRepository.findBylastName(username);
	}
	
	public String getUserRoleDescription(int id) {
		return userRepository.getUserRoleDescription(id);
		
	}
	
	@Override
	public List<String> findUsersActivated() throws Exception {
		return userRepository.getUsersFromActivated();
		
	}

	@Override
	public List<String> getUsersFromDisabled() {
		return userRepository.getUsersFromDisabled();
	}

}
