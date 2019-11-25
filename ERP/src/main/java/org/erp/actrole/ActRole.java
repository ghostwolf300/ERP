package org.erp.actrole;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="t_act_role")
public class ActRole {
	
	@Id
	@Column(name="id")
	private int id;
	@Column(name="name")
	private String name;
	
	public ActRole() {
		
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
