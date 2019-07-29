package org.erp.userrole;

import javax.persistence.CascadeType;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import org.erp.role.Role;
import org.erp.user.User;

@Entity
@Table(name="t_user_role")
public class UserRole {
	
	@EmbeddedId
	private UserRoleKey id;
	
	@ManyToOne(
			fetch=FetchType.LAZY
	)
	@MapsId("user_id")
	@JoinColumn(name="user_id")
	private User user;
	@ManyToOne(
			fetch=FetchType.LAZY
	)
	@MapsId("role_id")
	@JoinColumn(name="role_id")
	private Role role;
	
	public UserRole() {
	
	}
	
	public UserRole(UserRoleKey id) {
		this.id=id;
	}
	
	public UserRole(UserRoleKey id,User user,Role role) {
		this.id=id;
		this.user=user;
		this.role=role;
	}
	
	public UserRoleKey getId() {
		return id;
	}

	public void setId(UserRoleKey id) {
		this.id = id;
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
	
	
	
}
