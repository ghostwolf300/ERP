package org.erp.materialtype;

import java.io.Serializable;

public class MaterialTypeDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int id;
	private String shortName;
	private String name;
	
	public MaterialTypeDTO() {
		
	}
	
	public MaterialTypeDTO(MaterialType mt) {
		this.id=mt.getId();
		this.name=mt.getName();
		this.shortName=mt.getShortName();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
