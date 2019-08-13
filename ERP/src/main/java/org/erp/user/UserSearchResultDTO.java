package org.erp.user;

import java.io.Serializable;

public class UserSearchResultDTO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String id;
	private String lastName;
	private String firstName;
	private String email;
	
	public UserSearchResultDTO() {
		
	}

	public UserSearchResultDTO(String id, String lastName, String firstName, String email) {
		this.id = id;
		this.lastName = lastName;
		this.firstName = firstName;
		this.email = email;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
}
