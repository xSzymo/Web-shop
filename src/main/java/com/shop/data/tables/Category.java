package com.shop.data.tables;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Collection;
import java.util.LinkedHashSet;

@Entity
@Table(name = "categories")
public class Category {

	@Id
	@GeneratedValue
	@Column(name = "id")
	private Long id;

	@Column(name = "name")
	private String name;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
	private Collection<Book> books = new LinkedHashSet<Book>();

	public Category() {
		books = new LinkedHashSet<Book>();
	}

	public Category(String name) {
		this.name = name;
	}

	public Category(String name, Collection<Book> books) {
		this.name = name;
		this.books = books;
	}

	@Override
	public String toString() {
		return "Categories [id=" + id + ", name=" + name + ", books=" + books + "]";
	}

	public boolean compareTwoCategories(Category category) {
		boolean sameObject = true;
		if (!this.getId().equals(category.getId()))
			sameObject = false;
		if (!this.getName().equals(category.getName()))
			sameObject = false;
		for (Book x : this.getBooks()) {
			boolean saneBooks = false;
			for (Book x1 : category.getBooks())
				if (x.compareTwoBooks(x1)) {
					saneBooks = true;
					break;
				}
			if (!saneBooks) {
				sameObject = false;
				break;
			}
		}
		return sameObject;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public synchronized Collection<Book> getBooks() {
		if(books.size() < 1)
			return new LinkedHashSet<Book>();
		return books;
	}

	public void setBooks(Collection<Book> books) {
		this.books = books;
	}
}
