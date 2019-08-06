package org.erp.uom;

import java.io.Serializable;

public class UnitOfMeasureDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int id;
	private String name;
	private String isoCode;
	private String uomFor;
	
	public UnitOfMeasureDTO() {
		
	}
	
	public UnitOfMeasureDTO(UnitOfMeasure uom) {
		this.id=uom.getId();
		this.name=uom.getName();
		this.isoCode=uom.getIsoCode();
		this.uomFor=uom.getUomFor();
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

	public String getIsoCode() {
		return isoCode;
	}

	public void setIsoCode(String isoCode) {
		this.isoCode = isoCode;
	}

	public String getUomFor() {
		return uomFor;
	}

	public void setUomFor(String uomFor) {
		this.uomFor = uomFor;
	}

}
