package org.erp.address;

import java.io.Serializable;

public class AddressDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int id;
	private int typeId;
	private String street;
	private String houseNumber;
	private String postcode;
	private String city;
	private String countryIso;
	private int poBox;
	private String poBoxPostcode;
	
	public AddressDTO() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getTypeId() {
		return typeId;
	}

	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getHouseNumber() {
		return houseNumber;
	}

	public void setHouseNumber(String houseNumber) {
		this.houseNumber = houseNumber;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountryIso() {
		return countryIso;
	}

	public void setCountryIso(String countryIso) {
		this.countryIso = countryIso;
	}

	public int getPoBox() {
		return poBox;
	}

	public void setPoBox(int poBox) {
		this.poBox = poBox;
	}

	public String getPoBoxPostcode() {
		return poBoxPostcode;
	}

	public void setPoBoxPostcode(String poBoxPostcode) {
		this.poBoxPostcode = poBoxPostcode;
	}
	

}
