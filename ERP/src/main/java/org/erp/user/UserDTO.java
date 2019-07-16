package org.erp.user;

import java.io.Serializable;
import java.sql.Date;

public class UserDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String username;
	private String firstName;
	private String lastName;
	private String password;
	private String email;
	private boolean enabled;
	private boolean initialPw;
	private boolean locked;
	private Date validFrom;
	private Date validTo;
	
	
	public UserDTO() {
		
	}
	
	public UserDTO(User user) {
		this.username=user.getId();
		this.firstName=user.getFirstName();
		this.lastName=user.getLastName();
		this.email=user.getEmail();
		this.password=user.getPassword();
		this.enabled=user.isEnabled();
		this.locked=user.isLocked();
		this.initialPw=user.isInitialPw();
		this.validFrom=user.getValidFrom();
		this.validTo=user.getValidTo();
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String userName) {
		this.username = userName;
	}


	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public boolean isEnabled() {
		return enabled;
	}


	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isInitialPw() {
		return initialPw;
	}

	public void setInitialPw(boolean initialPw) {
		this.initialPw = initialPw;
	}

	public boolean isLocked() {
		return locked;
	}

	public void setLocked(boolean locked) {
		this.locked = locked;
	}

	public Date getValidFrom() {
		return validFrom;
	}

	public void setValidFrom(Date validFrom) {
		this.validFrom = validFrom;
	}

	public Date getValidTo() {
		return validTo;
	}

	public void setValidTo(Date validTo) {
		this.validTo = validTo;
	}
	

}
