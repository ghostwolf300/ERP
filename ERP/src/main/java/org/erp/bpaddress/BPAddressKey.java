package org.erp.bpaddress;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class BPAddressKey implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Column(name="bp_id")
	private String bpId;
	@Column(name="address_id")
	private int addressId;
	
	public BPAddressKey() {
		
	}
	
	public BPAddressKey(String bpId,int addressId) {
		this.bpId=bpId;
		this.addressId=addressId;
	}

	public String getBpId() {
		return bpId;
	}

	public void setBpId(String bpId) {
		this.bpId = bpId;
	}

	public int getAddressId() {
		return addressId;
	}

	public void setAddressId(int addressId) {
		this.addressId = addressId;
	}
	
}
