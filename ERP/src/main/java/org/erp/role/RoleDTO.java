package org.erp.role;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import org.erp.authobject.AuthObjectDTO;
import org.erp.roleobject.RoleObject;
import org.erp.roleobject.RoleObjectDTO;
import org.erp.userrole.UserRole;

public class RoleDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int id;
	private String name;
	private String description;
	private Set<RoleObjectDTO> roleObjects;
	
	public RoleDTO() {
		
	}
	
	public RoleDTO(RoleDTO role) {
		this.id=role.id;
		this.name=role.name;
		this.description=role.description;
		this.roleObjects=new HashSet<RoleObjectDTO>(role.roleObjects);
	}
	
	public RoleDTO(int id, String name,String description) {
		this.id=id;
		this.name=name;
		this.description=description;
	}
	
	public RoleDTO(UserRole ur) {
		this.id=ur.getId().getRoleId();
		this.name=ur.getRole().getName();
		this.description=ur.getRole().getDescription();
		
		if(roleObjects==null) {
			roleObjects=new HashSet<RoleObjectDTO>();
		}
		
		for(RoleObject ro :ur.getRole().getRoleObjects()) {
			RoleObjectDTO dtoRo=new RoleObjectDTO();
			dtoRo.setRole(new RoleDTO(this));
			dtoRo.setObject(new AuthObjectDTO(ro.getAuthObject()));
			dtoRo.setReadRights(ro.isReadRights());
			dtoRo.setUpdateRights(ro.isUpdateRights());
			dtoRo.setCreateRights(ro.isCreateRights());
			dtoRo.setDeleteRights(ro.isDeleteRights());
			roleObjects.add(dtoRo);
		}
	
	}
	
	public RoleDTO(Role r) {
		this.id=r.getId();
		this.name=r.getName();
		this.description=r.getDescription();
		if(roleObjects==null) {
			roleObjects=new HashSet<RoleObjectDTO>();
		}
		for(RoleObject ro :r.getRoleObjects()) {
			RoleObjectDTO dtoRo=new RoleObjectDTO();
			dtoRo.setRole(new RoleDTO(this));
			dtoRo.setObject(new AuthObjectDTO(ro.getAuthObject()));
			dtoRo.setReadRights(ro.isReadRights());
			dtoRo.setUpdateRights(ro.isUpdateRights());
			dtoRo.setCreateRights(ro.isCreateRights());
			dtoRo.setDeleteRights(ro.isDeleteRights());
			roleObjects.add(dtoRo);
		}
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	public Set<RoleObjectDTO> getRoleObjects() {
		return roleObjects;
	}

	public void setRoleObjects(Set<RoleObjectDTO> roleObjects) {
		this.roleObjects = roleObjects;
	}
	

}
