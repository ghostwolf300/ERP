package org.erp.role;

import java.util.ArrayList;
import java.util.List;

import org.erp.roleobject.RoleObject;
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

	@Override
	public List<RoleDTO> findAllRolesDTO() {
		List<Role> roles=roleRepository.findAll();
		List<RoleDTO> dtoList=new ArrayList<RoleDTO>();
		for(Role r : roles) {
			dtoList.add(new RoleDTO(r));
		}
		return dtoList;
	}

	@Override
	public RoleDTO findRoleDTO(int roleId) {
		Role role=roleRepository.findById(roleId);
		RoleDTO rdto=new RoleDTO(role);
		return rdto;
	}

}
