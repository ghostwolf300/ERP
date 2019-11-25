package org.erp.bpactrole;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class BPActRoleKey implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Column(name="bp_id")
	private String bpId;
	@Column(name="role_id")
	private int roleId;
	
	public BPActRoleKey() {
		
	}
	

}
