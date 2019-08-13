package org.erp.user;

import java.io.Serializable;

public class UserSearchParamDTO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String id;
	private String firstName;
	private String lastName;
	private String email;
	
	public UserSearchParamDTO() {
		
	}

	public UserSearchParamDTO(String id, String firstName, String lastName, String email) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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
	
	public void cleanParameters() {
		if(id!=null) {
			id=id.replace("*", "%");
		}
		if(firstName!=null) {
			firstName=firstName.replace("*","%");
		}
		if(lastName!=null) {
			lastName=lastName.replace("*","%");
		}
		if(email!=null) {
			email=email.replace("*", "%");
		}
	}
	
}
