package tn.esprit.pi.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.pi.entities.User;
import tn.esprit.pi.repositories.UserRepository;
import tn.esprit.pi.storage.UserStorage;

@RestController
@CrossOrigin
public class UserController {
	
	@Autowired
	UserRepository userRepository;
	
	@GetMapping("/registration/{idU}")
    public ResponseEntity<String> register(@PathVariable int idU) {
		User user = userRepository.findForChatById(idU);
        System.out.println("handling register user request: " + user);
        try {
            UserStorage.getInstance().setUser(user);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.status(HttpStatus.OK)
                .body("user connected: "+user);
    }

    @GetMapping("/fetchAllUsers")
    public List<User> fetchAll() {
        return UserStorage.getInstance().getUsers();
    }
}
