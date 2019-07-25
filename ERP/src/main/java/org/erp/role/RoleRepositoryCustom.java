package org.erp.role;

import java.util.List;

public interface RoleRepositoryCustom {
	
	public List<Role> findRolesAssigned(String username);
	public List<Role> findRolesNotAssigned(String username);
	
}
