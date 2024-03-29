package org.erp.role;

import java.util.List;

public interface RoleService {
	
	public List<Role> findAllRoles();
	public List<Role> findAssignedRoles(String username);
	public List<Role> findUnassignedRoles(String username);
	
	public List<RoleDTO> findAllRolesDTO();
	public RoleDTO findRoleDTO(int roleId);
	
	public RoleDTO saveRole(RoleDTO role);
	public RoleDTO removeRole(RoleDTO role);
	
	
}
