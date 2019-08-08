package org.erp.material;

public class MaterialSearchParamDTO {
	
	private String id;
	private String name;
	private String legacyId;
	
	public MaterialSearchParamDTO() {
		
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
	
	public void cleanParameters() {
		if(id!=null) {
			id=id.replace("*", "%");
		}
		if(name!=null) {
			name=name.replace("*","%");
		}
		if(legacyId!=null) {
			legacyId=legacyId.replace("*","%");
		}
	}
	
	
}
