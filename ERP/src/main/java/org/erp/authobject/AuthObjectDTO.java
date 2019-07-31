package org.erp.authobject;

import java.io.Serializable;

public class AuthObjectDTO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int id;
	private String name;
	
	public AuthObjectDTO() {
		
	}
	
	public AuthObjectDTO(int id,String name) {
		this.id=id;
		this.name=name;
	}
	
	public AuthObjectDTO(AuthObject au) {
		id=au.getId();
		name=au.getName();
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
