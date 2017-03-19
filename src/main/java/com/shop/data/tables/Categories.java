package com.shop.data.tables;

import java.util.Collection;
import java.util.LinkedHashSet;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.Hibernate;
import org.hibernate.annotations.Cascade;

@Entity
@Table(name = "categories")
public class Categories {

	@Id
	@GeneratedValue
	@Column(name = "category_id")
	private Long id;

	@Column(name = "category_name")
	private String name;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
	@JoinColumn(name = "categories_id") // here sssss
	private Collection<Books> books = new LinkedHashSet<Books>();

	public Categories() {
		books = new LinkedHashSet<Books>();
	}
	
	public Categories(String name) {
		this.name = name;
	}
	
	public Categories(String name, Collection<Books> books) {
		super();
		this.name = name;
		this.books = books;
	}

	public Categories(Long id, String name, Collection<Books> books) {
		this.id = id;
		this.name = name;
		this.books = books;
	}

	@Override
	public String toString() {
		return "Categories [id=" + id + ", name=" + name + ", books=" + books + "]";
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public synchronized Collection<Books> getBooks() {
		return books;
	}

	public void setBooks(Collection<Books> books) {
		this.books = books;
	}
}
