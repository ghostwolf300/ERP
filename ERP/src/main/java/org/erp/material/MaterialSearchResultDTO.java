package org.erp.material;

import java.io.Serializable;

public class MaterialSearchResultDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String id;
	private String name;
	private String legacyId;
	private String ean13;
	private String typeName;
	private String groupName;
	
	public MaterialSearchResultDTO() {
		
	}
	
	public MaterialSearchResultDTO(String id,String name,String legacyId,String ean13,String typeName,String groupName) {
		this.id=id;
		this.name=name;
		this.legacyId=legacyId;
		this.ean13=ean13;
		this.typeName=typeName;
		this.groupName=groupName;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLegacyId() {
		return legacyId;
	}

	public void setLegacyId(String legacyId) {
		this.legacyId = legacyId;
	}

	public String getEan13() {
		return ean13;
	}

	public void setEan13(String ean13) {
		this.ean13 = ean13;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
