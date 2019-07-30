package org.erp.authobject;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.erp.roleobject.RoleObject;

@Entity
@Table(name="t_auth_object")
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
