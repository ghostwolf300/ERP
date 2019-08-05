package org.erp.materialgroup;

import java.io.Serializable;

public class MaterialGroupDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int id;
	private String name;
	
	public MaterialGroupDTO() {
		
	}
	
	public MaterialGroupDTO(int id,String name) {
		this.id=id;
		this.name=name;
	}
	
	public MaterialGroupDTO(MaterialGroup mg) {
		this.id=mg.getId();
		this.name=mg.getName();
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

}
