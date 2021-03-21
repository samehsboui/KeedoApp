package tn.esprit.pi.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.fasterxml.jackson.annotation.JsonBackReference;

import tn.esprit.pi.entities.User;
import tn.esprit.pi.services.IUserservice;

@RestController
@RequestMapping("/User/Service")
public class UserController {

	@Autowired
	IUserservice iuserservice;

	@Autowired
	PasswordEncoder encoder;

	@JsonBackReference("")
	@PreAuthorize("hasAuthority('Admin') or hasAuthority('KindergardenDirector') or hasAuthority('DaycareManager') or hasAuthority('Doctor') or hasAuthority('Parent') or hasAuthority('visitor')")
	@GetMapping("/findall")
	public List<User> getAllUsers() {
		return iuserservice.getAllUsers();
	}

	@PreAuthorize("hasAuthority('Admin') or hasAuthority('KindergardenDirector') or hasAuthority('DaycareManager') or hasAuthority('Doctor') or hasAuthority('Parent') or hasAuthority('visitor')")
	@GetMapping("/userbyid/{idUser}")
	public User getUserById(@PathVariable("idUser") int idUser) throws Exception {
		return iuserservice.getUserById(idUser);
	}

	@PreAuthorize("hasAuthority('Admin') or hasAuthority('KindergardenDirector') or hasAuthority('DaycareManager') or hasAuthority('Doctor') or hasAuthority('Parent') or hasAuthority('visitor')")
	@PutMapping("/UpdateUser")
	@ResponseBody
	public User updateUser(@RequestBody User user) throws Exception {
		User userinthedatabase = iuserservice.getUserById(user.getIdUser());
		if (!encoder.encode(user.getPassword()).equals(userinthedatabase.getPassword())) {
			user.setPassword(encoder.encode(user.getPassword()));
		}
		return iuserservice.updateUser(user);
	}

	@PreAuthorize("hasAuthority('Admin') or hasAuthority('KindergardenDirector') or hasAuthority('DaycareManager') or hasAuthority('Doctor') or hasAuthority('Parent') or hasAuthority('visitor')")
	@DeleteMapping("/deleteUserById/{userId}")
	public void deleteUserById(@PathVariable("userId") Integer userId) throws Exception {
		iuserservice.deleteUserById(userId);
	}

	@PreAuthorize("hasAuthority('Admin') or hasAuthority('KindergardenDirector') or hasAuthority('DaycareManager') or hasAuthority('Doctor') or hasAuthority('Parent') or hasAuthority('visitor')")
	@PutMapping("/activateUser")
	public User activateUser(@RequestBody User user) throws Exception {
		return iuserservice.activateUser(user);
	}

	@PreAuthorize("hasAuthority('Admin') or hasAuthority('KindergardenDirector') or hasAuthority('DaycareManager') or hasAuthority('Doctor') or hasAuthority('Parent') or hasAuthority('visitor')")
	@PutMapping("/desactivateUser")
	public User desactivateUser(@RequestBody User user) throws Exception {
		return iuserservice.desactivateUser(user);
	}

	@PreAuthorize("hasAuthority('Admin') or hasAuthority('KindergardenDirector') or hasAuthority('DaycareManager') or hasAuthority('Doctor') or hasAuthority('Parent') or hasAuthority('visitor')")
	@GetMapping("/findUserLastName/{username}")
	public List<User> findUserLastName(@PathVariable("username") String username) throws Exception {
		return iuserservice.findUserLastName(username);
	}

	@PreAuthorize("hasAuthority('Admin') or hasAuthority('KindergardenDirector') or hasAuthority('DaycareManager') or hasAuthority('Doctor') or hasAuthority('Parent') or hasAuthority('visitor')")
	@GetMapping("/findUserBylogin/{username}")
	public User findUserBylogin(@PathVariable("username") String username) throws Exception {
		return iuserservice.findUserBylogin(username);
	}

	@PreAuthorize("hasAuthority('Admin') or hasAuthority('KindergardenDirector') or hasAuthority('DaycareManager') or hasAuthority('Doctor') or hasAuthority('Parent') or hasAuthority('visitor')")
	@GetMapping("/findUserRole/{IdUser}")
	public String findUserRole(@PathVariable("IdUser") int IdUser) throws Exception {
		return iuserservice.getUserRoleDescription(IdUser);
	}

	@PreAuthorize("hasAuthority('Admin') or hasAuthority('KindergardenDirector') or hasAuthority('DaycareManager') or hasAuthority('Doctor') or hasAuthority('Parent') or hasAuthority('visitor')")
	@GetMapping("/findActivatedUser/")
	public List<String> findUserActivated() throws Exception {
		return iuserservice.findUsersActivated();
	}

	@PreAuthorize("hasAuthority('Admin') or hasAuthority('KindergardenDirector') or hasAuthority('DaycareManager') or hasAuthority('Doctor') or hasAuthority('Parent') or hasAuthority('visitor')")
	@GetMapping("/findDisabledUser/")
	public List<String> findUserDisabled() throws Exception {
		return iuserservice.getUsersFromDisabled();
	}
}