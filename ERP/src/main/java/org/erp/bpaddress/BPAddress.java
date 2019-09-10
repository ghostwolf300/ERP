package org.erp.bpaddress;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import org.erp.address.Address;
import org.erp.bp.BusinessPartner;

@Entity
@Table(name="t_bp_address")
public class BPAddress {
	
	@EmbeddedId
	private BPAddressKey id;
	
	@ManyToOne(
			fetch=FetchType.LAZY
	)
	@MapsId("bp_id")
	@JoinColumn(name="bp_id")
	private BusinessPartner businessPartner;
	
	@ManyToOne(
			fetch=FetchType.LAZY
	)
	@MapsId("address_id")
	@JoinColumn(name="address_id")
	private Address address;
	
	@Column(name="valid_from")
	private Date validFrom;
	@Column(name="valid_to")
	private Date validTo;
	
	public BPAddress() {
		
	}
	
	public BPAddress(BPAddressKey id,Date validFrom,Date validTo) {
		this.id=id;
		this.validFrom=validFrom;
		this.validTo=validTo;
	}

	public BPAddressKey getId() {
		return id;
	}

	public void setId(BPAddressKey id) {
		this.id = id;
	}

	public Date getValidFrom() {
		return validFrom;
	}

	public void setValidFrom(Date validFrom) {
		this.validFrom = validFrom;
	}

	public Date getValidTo() {
		return validTo;
	}

	public void setValidTo(Date validTo) {
		this.validTo = validTo;
	}

	public BusinessPartner getBusinessPartner() {
		return businessPartner;
	}

	public void setBusinessPartner(BusinessPartner businessPartner) {
		this.businessPartner = businessPartner;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}
	
}
