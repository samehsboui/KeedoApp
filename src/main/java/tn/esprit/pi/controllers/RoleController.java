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

import tn.esprit.pi.entities.Role;
import tn.esprit.pi.services.IRoleservice;

@RestController
public class RoleController {

	@Autowired
	IRoleservice iroleservice;

	@JsonBackReference("")
	@GetMapping("/Role/findall")
	public List<Role> getAllUsers() {
		return iroleservice.getAllRoles();
	}

	@GetMapping("/Role/rolebyid/{idRole}")
	public Role getUserById(@PathVariable("idrole") int idrole) throws Exception {
		return iroleservice.findRoleById(idrole);
	}

	@PostMapping("/Role/createRole")
	@ResponseBody
	public Role createUser(@RequestBody Role user) throws Exception {
		return iroleservice.createRole(user);
	}

	@PutMapping("/Role/updateRole")
	@ResponseBody
	public Role updateUser(@RequestBody Role role) throws Exception {
		return iroleservice.updateRole(role);
	}

	@DeleteMapping("/Role/deleteRoleById/{roleId}")
	public void deleteRoleById(@PathVariable("roleId") int roleId) throws Exception {
		iroleservice.deleteRoleById(roleId);
	}
}