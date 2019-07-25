package org.erp.userrole;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class UserRoleKey implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Column(name="user_id")
	private String userId;
	@Column(name="role_id")
	private int roleId;
	
	public UserRoleKey() {
		
	}
	
	public UserRoleKey(String userId,int roleId) {
		this.userId=userId;
		this.roleId=roleId;
	}
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public int getRoleId() {
		return roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	
}
