package org.erp.role;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.ConstructorResult;
import javax.persistence.ColumnResult;

import org.erp.userrole.UserRole;

@Entity
@Table(name="t_role")
@SqlResultSetMapping(name="RolesNotAssigned",
classes= {
		@ConstructorResult(targetClass=org.erp.role.Role.class,
				columns={
					@ColumnResult(name="id"),
					@ColumnResult(name="name"),
					@ColumnResult(name="description")
		})
})
public class Role {
	
	@Id
	@Column(name="id")
	private int id;
	@Column(name="name")
	private String name;
	@Column(name="description")
	private String description;
	
	//@ManyToMany(mappedBy="userRoles")
	//private Set<User> users;
	
	@OneToMany(mappedBy="role")
	private Set<UserRole> userRoles;
	
	
	public Role() {
		
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

	/*
	 * public Set<User> getUsers() { return users; }
	 * 
	 * public void setUsers(Set<User> users) { this.users = users; }
	 */

	public Set<UserRole> getUserRoles() {
		return userRoles;
	}

	public void setUserRoles(Set<UserRole> userRoles) {
		this.userRoles = userRoles;
	}
	
}
