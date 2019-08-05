package org.erp.material;

import java.io.Serializable;

import org.erp.materialtype.MaterialTypeDTO;

public class MaterialDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String id;
	private String legacyId;
	private String name;
	private String ean13;
	private MaterialTypeDTO materialType;

	
	
	public MaterialDTO() {
		
	}
	
	public MaterialDTO(String id) {
		this.id=id;
	}
	
	public MaterialDTO(Material m) {
		this.id=m.getId();
		this.legacyId=m.getLegacyId();
		this.name=m.getName();
		this.ean13=m.getEan13();
		this.materialType=new MaterialTypeDTO(m.getMaterialType());
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


	public MaterialTypeDTO getMaterialType() {
		return materialType;
	}

	public void setMaterialType(MaterialTypeDTO materialType) {
		this.materialType = materialType;
	}
}