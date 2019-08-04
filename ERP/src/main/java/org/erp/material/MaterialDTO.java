package org.erp.material;

import java.io.Serializable;

public class MaterialDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String id;
	private String legacyId;
	private String name;
	private String ean13;
	
	
	public MaterialDTO() {
		
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getLegacyId() {
		return legacyId;
	}


	public void setLegacyId(String legacyId) {
		this.legacyId = legacyId;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getEan13() {
		return ean13;
	}


	public void setEan13(String ean13) {
		this.ean13 = ean13;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
