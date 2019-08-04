package org.erp.materialtype;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="t_material_type")
public class MaterialType {
	
	@Id
	@Column(name="id")
	private int id;
	@Column(name="short_name")
	private String shortName;
	@Column(name="name")
	private String name;
	
	public MaterialType() {
		
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
	
}
