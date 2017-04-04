package com.shop.data.tables;

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
	@Column(name = "age")
	private int age;

	@OneToOne
	@JoinColumn(name = "address_id")
	private Address address;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
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

	public Users(String login, String password, String name, String surname, String eMail, int old,
			boolean isAdmin, Address address, Collection<Orders> orders) {
		this.login = login;
		this.password = password;
		this.name = name;
		this.surname = surname;
		this.eMail = eMail;
		this.age = old;
		this.setAddress(address);
		this.setOrders(orders);
	}

	public Users(Users user) {
		this.login = user.login;
		this.password = user.password;
		this.name = user.name;
		this.surname = user.surname;
		this.eMail = user.eMail;
		this.age = user.age;
		this.setAddress(user.address);
		this.setOrders(user.orders);
	}

	public Users(String login, String password, String name, String surname, String eMail) {
		super();
		this.login = login;
		this.password = password;
		this.name = name;
		this.surname = surname;
		this.eMail = eMail;
	}

	@Override
	public String toString() {
		return "Users [id=" + id + ", login=" + login + ", password=" + password + ", name=" + name + ", surname="
				+ surname + ", eMail=" + eMail + ", old=" + age + ", address=" + getAddress() + ", orders="
				+ getOrders() + "]";
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

	public int getAge() {
		return this.age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Collection<Orders> getOrders() {
		return orders;
	}

	public void setOrders(Collection<Orders> orders) {
		this.orders = orders;
	}
}
