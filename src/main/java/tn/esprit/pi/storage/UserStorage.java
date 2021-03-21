package tn.esprit.pi.storage;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import tn.esprit.pi.entities.User;
import tn.esprit.pi.repositories.IUserRepository;

public class UserStorage {
	private static UserStorage instance;
	private List<User> users;

	@Autowired
	IUserRepository userRepository;

	private UserStorage() {
		users = new ArrayList<User>();
	}

	public static synchronized UserStorage getInstance() {
		System.out.println("storage!!");
		if (instance == null) {
			instance = new UserStorage();
		}
		return instance;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUser(User user) throws Exception {
		System.out.println(user);
		if (users.contains(user)) {
			throw new Exception("User aready exists with userId: " + user.getIdUser());
		}
		users.add(user);
		System.out.println("test user 2: " + users);
	}
}
