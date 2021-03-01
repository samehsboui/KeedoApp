package tn.esprit.pi.services;

import java.util.List;

import tn.esprit.pi.entities.Role;

public interface IRoleservice {
	public List<Role> getAllRoles();
	public Role createRole(Role entity) throws Exception;
	public Role updateRole(Role entity) throws Exception;
	public Role findRoleByroleType(String user) throws Exception;
	public Role findRoleById(int id) throws Exception;
	public void deleteRoleById(int roleId) throws Exception;
}
