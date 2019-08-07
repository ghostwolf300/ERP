package org.erp.materialgroup;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="t_material_group")
public class MaterialGroup {
	@Id
	@Column(name="id")
	private int id;
	@Column(name="name")
	private String name;
	
	public MaterialGroup() {
		
	}
	
	public MaterialGroup(MaterialGroupDTO group) {
		this.id=group.getId();
		this.name=group.getName();
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
