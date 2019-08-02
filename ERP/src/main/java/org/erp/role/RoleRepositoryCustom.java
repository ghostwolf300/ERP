package org.erp.role;

import java.util.List;

import javax.transaction.Transactional;

public interface RoleRepositoryCustom {
	
	public List<Role> findRolesAssigned(String username);
	public List<Role> findRolesNotAssigned(String username);
	
	@Transactional
	public Role mergeRole(Role role);
	
}
