package tn.esprit.pi.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.fasterxml.jackson.annotation.JsonBackReference;

import tn.esprit.pi.entities.User;
import tn.esprit.pi.services.IUserservice;

@RestController
public class UserController {

	@Autowired
	IUserservice iuserservice;

	@JsonBackReference("")
	@GetMapping("/User/findall")
	public List<User> getAllUsers() {
		return iuserservice.getAllUsers();
	}

	@GetMapping("/User/userbyid/{idUser}")
	public User getUserById(@PathVariable("idUser") String idUser) throws Exception {
		return iuserservice.getUserById(Integer.valueOf(idUser));
	}

	@PostMapping("/User/createUser")
	@ResponseBody
	public User createUser(@RequestBody User user) throws Exception {
		return iuserservice.createUser(user);
	}

	@PutMapping("/User/UpdateUser")
	@ResponseBody
	public User updateUser(@RequestBody User user) throws Exception {
		return iuserservice.updateUser(user);
	}

	@DeleteMapping("/User/deleteUserById/{userId}")
	public void deleteUserById(@PathVariable("userId") Integer userId) throws Exception {
		iuserservice.deleteUserById(userId);
	}

	@PutMapping("/User/activateUser")
	public User activateUser(@RequestBody User user) throws Exception {
		return iuserservice.activateUser(user);
	}

	@PutMapping("/User/desactivateUser")
	public User desactivateUser(@RequestBody User user) throws Exception {
		return iuserservice.desactivateUser(user);
	}
	
	@GetMapping("/User/findUserLastName/{username}")
	public List<User> findUserLastName(@PathVariable("username") String username) throws Exception {
		return iuserservice.findUserLastName(username);
	}
	
	@GetMapping("/User/findUserBylogin/{username}")
	public User findUserBylogin(@PathVariable("username") String username) throws Exception {
		return iuserservice.findUserBylogin(username);
	}
}