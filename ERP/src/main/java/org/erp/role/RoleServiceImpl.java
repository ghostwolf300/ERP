package org.erp.role;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("roleService")
public class RoleServiceImpl implements RoleService {
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Override
	public List<Role> findAssignedRoles(String username) {
		List<Role> roles=roleRepository.findRolesAssigned(username);
		return roles;
	}

	@Override
	public List<Role> findUnassignedRoles(String username) {
		List<Role> roles=roleRepository.findRolesNotAssigned(username);
		return roles;
	}

	@Override
	public List<Role> findAllRoles() {
		List<Role> roles=roleRepository.findAll();
		return roles;
	}

}
