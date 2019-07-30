package org.erp.roleobject;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import org.erp.authobject.AuthObject;
import org.erp.role.Role;

@Entity
@Table(name="t_role_auth_object")
public class RoleObject {
	
	@EmbeddedId
	private RoleObjectKey id;
	
	@ManyToOne(
			fetch=FetchType.LAZY
	)
	@MapsId("role_id")
	@JoinColumn(name="role_id")
	private Role authRole;
	
	@ManyToOne(
			fetch=FetchType.LAZY
	)
	@MapsId("object_id")
	@JoinColumn(name="object_id")
	private AuthObject authObject;
	
	@Column(name="read_access")
	private boolean readAccess;
	@Column(name="write_access")
	private boolean writeAccess;
	
	public RoleObject() {
		
	}
	
	public RoleObject(RoleObjectKey id,boolean readAccess,boolean writeAccess) {
		this.id=id;
		this.readAccess=readAccess;
		this.writeAccess=writeAccess;
	}
	
	public RoleObjectKey getId() {
		return id;
	}
	public void setId(RoleObjectKey id) {
		this.id = id;
	}
	public Role getAuthRole() {
		return authRole;
	}
	public void setAuthRole(Role role) {
		this.authRole = role;
	}
	public AuthObject getAuthObject() {
		return authObject;
	}
	public void setAuthObject(AuthObject object) {
		this.authObject = object;
	}
	public boolean isReadAccess() {
		return readAccess;
	}
	public void setReadAccess(boolean readAccess) {
		this.readAccess = readAccess;
	}
	public boolean isWriteAccess() {
		return writeAccess;
	}
	public void setWriteAccess(boolean writeAccess) {
		this.writeAccess = writeAccess;
	}
	
}
