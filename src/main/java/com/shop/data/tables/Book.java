package com.shop.data.tables;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.LinkedList;

@Entity
@Table(name = "books")
public class Book {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;

	@Column(name = "name")
	private String name;
	@Column(name = "author")
	private String author;
	@Column(name = "language")
	private String language;
	@Column(name = "description", length = 512)
	private String description;
	@Column(name = "price")
	private BigDecimal price;

	@ManyToOne
	@CollectionTable(name = "category_id")
	private Category category;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	@JoinColumn(name = "books_id")
	private Collection<Picture> pictures = new LinkedList<Picture>();

	public Book() {

	}

	public Book(String name) {
		this.name = name;
	}

	public Book(String name, String author, String language, String description, BigDecimal price) {
		this.name = name;
		this.author = author;
		this.language = language;
		this.description = description;
		this.price = price;
	}

	public Book(String name, String author, String language, String description, BigDecimal price,
	            Collection<Picture> pictures) {
		this.name = name;
		this.author = author;
		this.language = language;
		this.description = description;
		this.price = price;
		this.pictures = pictures;
	}

	public boolean compareTwoBooks(Book book) {
		boolean sameObjects = true;
		sameObjects = compareTwoBooksIsAnyFieldNull(book, sameObjects);
		if(sameObjects == false)
			return false;
		sameObjects = compareTwoBooksWithFields(book, sameObjects);
		return sameObjects;
	}

	@Override
	public String toString() {
		return "Books [id=" + id + ", name=" + name + ", author=" + author + ", language=" + language + ", description="
				+ description + ", price=" + price + ", pictures=" + pictures + "]";
	}

	public Long getId() {
		return id;
	}


	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Collection<Picture> getPictures() {
		return pictures;
	}

	public void setPictures(Collection<Picture> pictures) {
		this.pictures = pictures;
	}

	private boolean compareTwoBooksIsAnyFieldNull(Book book, boolean sameObjects) {
		if (this.id != null && book.getId() == null || this.id == null && book.getId() != null)
			sameObjects = false;
		if (this.category != null && book.getCategory() == null || this.id == null && book.getCategory() != null)
			sameObjects = false;
		if (this.description != null && book.getDescription() == null || this.description == null && book.getDescription() != null)
			sameObjects = false;
		if (this.language != null && book.getLanguage() == null || this.language == null && book.getLanguage() != null)
			sameObjects = false;
		if (this.author != null && book.getAuthor() == null || this.author == null && book.getAuthor() != null)
			sameObjects = false;
		if (this.price != null && book.getPrice() == null || this.price == null && book.getPrice() != null)
			sameObjects = false;
		return sameObjects;
	}

	private boolean compareTwoBooksWithFields(Book book, boolean sameObjects) {
		if (this.id != null && book.getId() != null)
			if (!this.id.equals(book.getId()))
				sameObjects = false;
		if (this.category != null && book.getCategory() != null)
			if (!this.category.getId().equals(book.getCategory().getId()))
				sameObjects = false;
		if (this.description != null && book.getDescription() != null)
			if (!this.description.equals(book.getDescription()))
				sameObjects = false;
		if (this.language != null && book.getLanguage() != null)
			if (!this.language.equals(book.getLanguage()))
				sameObjects = false;
		if (this.author != null && book.getAuthor() != null)
			if (!this.author.equals(book.getAuthor()))
				sameObjects = false;
		if (this.price != null && book.getPrice() != null)
			if (!this.price.equals(book.getPrice()))
				sameObjects = false;
		return sameObjects;
	}
}
