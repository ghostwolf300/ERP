package org.erp.authobject;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityResult;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;
import javax.persistence.OneToMany;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.Table;

import org.erp.roleobject.RoleObject;
import org.springframework.security.core.GrantedAuthority;

@Entity
@Table(name="t_auth_object")
@NamedNativeQuery(
		name="UnassignedObjects",
		query="select o.id,o.name from t_auth_object o where o.id not in (select object_id from t_role_auth_object where role_id=:roleId)",
		resultSetMapping="UnassignedObjectsResult"
)
@SqlResultSetMapping(
name="UnassignedObjectsResult",
entities={
		@EntityResult(
				entityClass=org.erp.authobject.AuthObject.class
		)
})
public class AuthObject {
	
	@Id
	@Column(name="id")
	private int id;
	@Column(name="name")
	private String name;
	
	@OneToMany(
			mappedBy="authObject",
			fetch=FetchType.LAZY,
			cascade=CascadeType.ALL,
			orphanRemoval=true
	)
	private Set<RoleObject> roleObjects;
	
	
	public AuthObject() {
		
	}
	
	public AuthObject(int id,String name) {
		this.id=id;
		this.name=name;
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

	public Set<RoleObject> getRoleObjects() {
		return roleObjects;
	}

	public void setRoleObjects(Set<RoleObject> roleObjects) {
		this.roleObjects = roleObjects;
	}
	
}
