package tn.esprit.pi.controllers;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import tn.esprit.pi.security.jwt.JwtUtils;
import tn.esprit.pi.entities.User;
import tn.esprit.pi.payload.request.LoginRequest;
import tn.esprit.pi.payload.response.JwtResponse;
import tn.esprit.pi.payload.response.MessageResponse;
import tn.esprit.pi.security.services.UserDetailsImpl;
import tn.esprit.pi.services.IUserservice;
import tn.esprit.pi.services.MailingService;
import tn.esprit.pi.services.UserService;
import tn.esprit.pi.services.IRoleservice;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/User/Access")
public class AuthController {
	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	IUserservice iuserservice;

	@Autowired
	IRoleservice iroleservice;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	JwtUtils jwtUtils;

	@PostMapping("/login")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);
		
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();		
		List<String> roles = userDetails.getAuthorities().stream()
				.map(item -> item.getAuthority())
				.collect(Collectors.toList());

		return ResponseEntity.ok(new JwtResponse(jwt,
												 userDetails.getUsername(), 
												 roles.get(0).toString()));
	}
	
	@PostMapping("/signup")
	@ResponseBody
	public ResponseEntity<?> createUser(@RequestBody User user) throws Exception {
		if (user == null) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error: please add values!"));
		}
		if (user.getAddress().equals("")) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error: please add address!"));
		}
		if (user.getAddress().equals("")) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error: please add bithday date!"));
		}
		if (!(user.getBirthdate() instanceof Date)) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error: please add bithday date!"));
		}
		if (user.getFirstName().equals("")) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error: please add your first name!"));
		}
		if (user.getMail().equals("") || !UserService.validate(user.getMail())) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error: please check your mail!"));
		}
		if (iuserservice.findUserBylogin(user.getLogin()) != null) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error: Username is already taken!"));
		}
		// Create new user's account
		user.setPassword(encoder.encode(user.getPassword()));
		if (user.getRole() ==null) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error: Could you please add a role for the new user!"));
		}
		user.setValid(true);
		user.setAccountNonLocked(true);
		user.setFailedAttempt(0);
		iuserservice.createUser(user);
		MailingService.sendMail(user.getMail(), user.getLastName(), user.getRole().getRoleType().name());
		return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
	}
}