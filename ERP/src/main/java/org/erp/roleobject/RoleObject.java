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
import org.springframework.security.core.GrantedAuthority;

@Entity
@Table(name="t_role_auth_object")
public class RoleObject implements GrantedAuthority {
	
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
	
	@Column(name="read_rights")
	private boolean readRights;
	@Column(name="update_rights")
	private boolean updateRights;
	@Column(name="create_rights")
	private boolean createRights;
	@Column(name="delete_rights")
	private boolean deleteRights;
	
	public RoleObject() {
		
	}
	
	public RoleObject(RoleObjectKey id,boolean readAccess,boolean writeAccess) {
		this.id=id;
		this.readRights=readAccess;
		this.updateRights=writeAccess;
	}
	
	public RoleObject(RoleObjectDTO dto) {
		this.id=new RoleObjectKey(dto.getRole().getId(),dto.getObject().getId());
		this.readRights=dto.isReadRights();
		this.updateRights=dto.isUpdateRights();
		this.createRights=dto.isCreateRights();
		this.deleteRights=dto.isDeleteRights();
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
	public boolean isReadRights() {
		return readRights;
	}
	public void setReadRights(boolean readAccess) {
		this.readRights = readAccess;
	}
	public boolean isUpdateRights() {
		return updateRights;
	}
	public void setUpdateRights(boolean writeAccess) {
		this.updateRights = writeAccess;
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

	@Override
	public String getAuthority() {
		String authority=authObject.getId()+";display="+this.readRights+";update="+this.updateRights+";create="+this.createRights+";delete="+this.deleteRights;
		return authority;
	}
	
}
