package tn.esprit.pi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;


import tn.esprit.pi.entities.Role;
import tn.esprit.pi.entities.RoleType;

public interface IRoleRepository extends JpaRepository<Role, Integer> {
	public Role findRoleByroleType(RoleType user) throws Exception;
	
	/*
	@Query("SELECT  r From Role r WHERE r.roleType =:role ")

	public Role findParent(@Param("role")String role) ;
*/

	public Role findRoleByroleType(String user) throws Exception;
	
	public Role findRoleByidRole(int user) throws Exception;

}