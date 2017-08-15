package com.shop.data.tables;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Collection;
import java.util.LinkedHashSet;

@Entity
@Table(name = "categories")
public class Category {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
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

	//TODO - check are books same
//	@Override
//	public boolean equals(Object o) {
//		if (this == o) return true;
//		if (o == null || getClass() != o.getClass()) return false;
//
//		Category category = (Category) o;
//
//		if (!name.equals(category.name)) return false;
////		return books != null ? books.equals(category.books) : category.books == null;
//		return true;
//	}

	public boolean equals(Category category) {
		boolean sameObject = true;
		if (!this.getId().equals(category.getId()))
			sameObject = false;
		if (!this.getName().equals(category.getName()))
			sameObject = false;
		for (Book x : this.getBooks()) {
			boolean saneBooks = false;
			for (Book x1 : category.getBooks())
				if (x.equals(x1)) {
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

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public synchronized Collection<Book> getBooks() {
//		if(books.size() < 1)
//			return new LinkedHashSet<Book>();
		return books;
	}

	public void setBooks(Collection<Book> books) {
//		this.books.removeAll(this.books);
//		this.books = new LinkedHashSet<>();
//		this.books.addAll(books);
		this.books = books;
	}
}
