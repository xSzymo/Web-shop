package com.shop.data.tables;

import javax.persistence.*;
import java.util.Collection;
import java.util.LinkedHashSet;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Column(name = "login")
    private String login;
    @Column(name = "password")
    private String password;
    @Column(name = "name")
    private String name;
    @Column(name = "surname")
    private String surname;
    @Column(name = "e_mail")
    private String eMail;
    @Column(name = "age")
    private int age;
    @Column(name = "cookie_code")
    private String cookieCode;

    @OneToOne
    @JoinColumn(name = "address_id")
    private Address address;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "user_id")
    private Collection<Order> orders = new LinkedHashSet<Order>();

    public User() {

    }

    public User(String login, String password, String eMail) {
        this.login = login;
        this.password = password;
        this.eMail = eMail;
    }

    public User(String login, String password, String name, String surname, String eMail, int old, boolean isAdmin,
                Address address, Collection<Order> orders) {
        this.login = login;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.eMail = eMail;
        this.age = old;
        this.setAddress(address);
        this.setOrders(orders);
    }

    public User(User user) {
        this.login = user.login;
        this.password = user.password;
        this.name = user.name;
        this.surname = user.surname;
        this.eMail = user.eMail;
        this.age = user.age;
        this.setAddress(user.address);
        this.setOrders(user.orders);
    }

    public User(String login, String password, String name, String surname, String eMail) {
        this.login = login;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.eMail = eMail;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (!login.equals(user.login)) return false;
        return eMail.equals(user.eMail);
    }

    @Override
    public int hashCode() {
        int result = login.hashCode();
        result = 31 * result + eMail.hashCode();
        return result;
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

    public Collection<Order> getOrders() {
        return orders;
    }

    public void setOrders(Collection<Order> orders) {
        this.orders = orders;
    }

    public String getCookieCode() {
        return cookieCode;
    }

    public void setCookieCode(String cookieCode) {
        this.cookieCode = cookieCode;
    }
}
