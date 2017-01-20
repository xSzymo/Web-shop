package com.shop.data.tables;

import java.sql.Date;
import java.util.Collection;
import java.util.LinkedHashSet;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class Users {

	@Id
	@GeneratedValue
	@Column(name = "user_id")
	private Long id;

	@Column(name = "user_login")
	private String login;
	@Column(name = "user_password")
	private String password;
	@Column(name = "user_name")
	private String name;
	@Column(name = "user_surname")
	private String surname;
	@Column(name = "user_e_mail")
	private String eMail;
	@Column(name = "user_date_birth")
	private Date dateBirth;
	@Column(name = "user_is_admin")
	private boolean isAdmin;

	@OneToOne
	@JoinColumn(name = "addr123ess_id")
	private Address address;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id")
	private Collection<Orders> orders = new LinkedHashSet<Orders>();

	public Users() {

	}

	public Users(String login, String password, String eMail) {
		super();
		this.login = login;
		this.password = password;
		this.eMail = eMail;
	}

	public Users(String login, String password, String name, String surname, String eMail, Date dateBirth,
			boolean isAdmin, Address address, Collection<Orders> orders) {
		this.login = login;
		this.password = password;
		this.name = name;
		this.surname = surname;
		this.eMail = eMail;
		this.dateBirth = dateBirth;
		this.isAdmin = isAdmin;
		this.address = address;
		this.orders = orders;
	}

	@Override
	public String toString() {
		return "Users [id=" + id + ", login=" + login + ", password=" + password + ", name=" + name + ", surname="
				+ surname + ", eMail=" + eMail + ", dateBirth=" + dateBirth + ", isAdmin=" + isAdmin + ", address="
				+ address + ", orders=" + orders + "]";
	}

	public Long getId() {
		return id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String geteMail() {
		return eMail;
	}

	public void seteMail(String eMail) {
		this.eMail = eMail;
	}

	public Date getDateBirth() {
		return dateBirth;
	}

	public void setDateBirth(Date dateBirth) {
		this.dateBirth = dateBirth;
	}

	public boolean isAdmin() {
		return isAdmin;
	}

	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

}
