package com.shop.data.tables;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "address")
public class Address {
	@Id
	@GeneratedValue
	@Column(name = "address_id")
	private Long id;

	@Column(name = "address_street")
	private String street;
	@Column(name = "address_postal_code")
	private String postalCode;
	@Column(name = "address_city")
	private String city;
	@Column(name = "address_country")
	private String country;

	public Address() {

	}

	public Long getId() {
		return id;
	}

	public Address(String street, String postalCode, String city, String country) {
		super();
		this.street = street;
		this.postalCode = postalCode;
		this.city = city;
		this.country = country;
	}

	public Address(Long id, String street, String postalCode, String city, String country) {
		this.id = id;
		this.street = street;
		this.postalCode = postalCode;
		this.city = city;
		this.country = country;
	}

	@Override
	public String toString() {
		return "Address [id=" + id + ", street=" + street + ", postalCode=" + postalCode + ", city=" + city
				+ ", country=" + country + "]";
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}
}
