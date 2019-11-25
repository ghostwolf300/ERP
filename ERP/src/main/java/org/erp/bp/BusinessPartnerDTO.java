package org.erp.bp;

import java.io.Serializable;
import java.util.Set;

import org.erp.address.AddressDTO;

public class BusinessPartnerDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String id;
	private int groupId;
	private String name1;
	private String name2;
	private String name3;
	private String name4;
	private String keyword1;
	private String keyword2;
	private Set<AddressDTO> addresses=null;
	
	public BusinessPartnerDTO() {
		
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getGroupId() {
		return groupId;
	}

	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}

	public String getName1() {
		return name1;
	}

	public void setName1(String name1) {
		this.name1 = name1;
	}

	public String getName2() {
		return name2;
	}

	public void setName2(String name2) {
		this.name2 = name2;
	}

	public String getName3() {
		return name3;
	}

	public void setName3(String name3) {
		this.name3 = name3;
	}

	public String getName4() {
		return name4;
	}

	public void setName4(String name4) {
		this.name4 = name4;
	}

	public String getKeyword1() {
		return keyword1;
	}

	public void setKeyword1(String keyword1) {
		this.keyword1 = keyword1;
	}

	public String getKeyword2() {
		return keyword2;
	}

	public void setKeyword2(String keyword2) {
		this.keyword2 = keyword2;
	}

	public Set<AddressDTO> getAddresses() {
		return addresses;
	}

	public void setAddresses(Set<AddressDTO> addresses) {
		this.addresses = addresses;
	}
	

}
