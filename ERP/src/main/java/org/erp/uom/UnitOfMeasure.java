package org.erp.uom;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="t_uom")
public class UnitOfMeasure {
	
	@Id
	@Column(name="id")
	private int id;
	@Column(name="name")
	private String name;
	@Column(name="iso_code")
	private String isoCode;
	@Column(name="uom_for")
	private String uomFor;
	
	public UnitOfMeasure() {
		
	}
	
	public UnitOfMeasure(UnitOfMeasureDTO uom) {
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
