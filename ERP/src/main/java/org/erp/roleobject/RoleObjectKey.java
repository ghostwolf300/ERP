package org.erp.roleobject;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class RoleObjectKey implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Column(name="role_id")
	private int roleId;
	@Column(name="object_id")
	private int objectId;
	
	public RoleObjectKey() {
		
	}
	
	public RoleObjectKey(int roleId, int objectId) {
		this.roleId=roleId;
		this.objectId=objectId;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public int getObjectId() {
		return objectId;
	}

	public void setObjectId(int objectId) {
		this.objectId = objectId;
	}
	
}
