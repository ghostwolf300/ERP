package org.erp.roleobject;

import java.io.Serializable;

import org.erp.authobject.AuthObjectDTO;
import org.erp.role.RoleDTO;

public class RoleObjectDTO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private RoleDTO role;
	private AuthObjectDTO object;
	private boolean readRights;
	private boolean updateRights;
	private boolean createRights;
	private boolean deleteRights;
	
	public RoleObjectDTO() {
		
	}
	
	public RoleObjectDTO(RoleDTO role,AuthObjectDTO object) {
		this.role=role;
		this.object=object;
	}
	
	
	public RoleObjectDTO(RoleObject ro) { 
		role=new RoleDTO(ro.getAuthRole());
		object=new AuthObjectDTO(ro.getAuthObject()); 
		readRights=ro.isReadRights();
		updateRights=ro.isUpdateRights(); 
		createRights=ro.isCreateRights();
		deleteRights=ro.isDeleteRights(); 
	}
	 

	public RoleDTO getRole() {
		return role;
	}

	public void setRole(RoleDTO role) {
		this.role = role;
	}

	public AuthObjectDTO getObject() {
		return object;
	}

	public void setObject(AuthObjectDTO object) {
		this.object = object;
	}

	public boolean isReadRights() {
		return readRights;
	}

	public void setReadRights(boolean readRights) {
		this.readRights = readRights;
	}

	public boolean isUpdateRights() {
		return updateRights;
	}

	public void setUpdateRights(boolean updateRights) {
		this.updateRights = updateRights;
	}

	public boolean isCreateRights() {
		return createRights;
	}

	public void setCreateRights(boolean createRights) {
		this.createRights = createRights;
	}

	public boolean isDeleteRights() {
		return deleteRights;
	}

	public void setDeleteRights(boolean deleteRights) {
		this.deleteRights = deleteRights;
	}
	
	
	
}
