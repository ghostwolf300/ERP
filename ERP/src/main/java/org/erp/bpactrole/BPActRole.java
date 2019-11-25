package org.erp.bpactrole;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import org.erp.actrole.ActRole;
import org.erp.bp.BusinessPartner;

@Entity
@Table(name="t_bp_act_role")
public class BPActRole {
	
	@EmbeddedId
	private BPActRoleKey id;
	
	@ManyToOne(
			fetch=FetchType.LAZY
	)
	@MapsId("bp_id")
	@JoinColumn(name="bp_id")
	private BusinessPartner businessPartner;

	@ManyToOne(
			fetch=FetchType.LAZY
	)
	@MapsId("role_id")
	@JoinColumn(name="role_id")
	private ActRole actRole;
	
	public BPActRole() {
		
	}

	public BPActRoleKey getId() {
		return id;
	}

	public void setId(BPActRoleKey id) {
		this.id = id;
	}

	public BusinessPartner getBusinessPartner() {
		return businessPartner;
	}

	public void setBusinessPartner(BusinessPartner businessPartner) {
		this.businessPartner = businessPartner;
	}

	public ActRole getRole() {
		return actRole;
	}

	public void setRole(ActRole actRole) {
		this.actRole = actRole;
	}
	
	
}
