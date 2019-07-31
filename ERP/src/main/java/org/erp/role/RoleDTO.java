package org.erp.role;

import java.io.Serializable;

import org.erp.userrole.UserRole;

public class RoleDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int id;
	private String name;
	private String description;
	
	public RoleDTO() {
		
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
	}
	
	public RoleDTO(Role r) {
		this.id=r.getId();
		this.name=r.getName();
		this.description=r.getDescription();
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
	

}
