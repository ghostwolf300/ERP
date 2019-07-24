package com.erp.role;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.erp.user.User;

@Entity
@Table(name="t_role")
public class Role {
	
	@Id
	@Column(name="id")
	private int id;
	@Column(name="name")
	private String name;
	@Column(name="description")
	private String description;
	@ManyToMany(mappedBy="userRoles")
	private Set<User> users;
	
	
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

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}
	
}
