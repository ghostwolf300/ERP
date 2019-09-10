package org.erp.address;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="t_address")
public class Address {
	
	@Id
	@Column(name="id")
	private int id;
	@Column(name="type_id")
	private int typeId;
	@Column(name="street")
	private String street;
	@Column(name="house_num")
	private String houseNumber;
	@Column(name="postcode")
	private String postcode;
	@Column(name="city")
	private String city;
	@Column(name="country_iso")
	private String countryIso;
	@Column(name="po_box")
	private int poBox;
	@Column(name="po_box_pc")
	private String poBoxPostcode;
	
	public Address() {
		
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
