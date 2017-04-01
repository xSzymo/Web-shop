package com.shop.data.tables;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.LinkedList;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "books")
public class Books {

	@Id
	@GeneratedValue
	@Column(name = "book_id")
	private Long id;

	@Column(name = "book_name")
	private String name;
	@Column(name = "book_author")
	private String author;
	@Column(name = "book_language")
	private String language;
	@Column(name = "book_description", length = 512)
	private String description;
	@Column(name = "book_price")
	private BigDecimal price;
	
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	@JoinColumn(name = "book_id")
	private Collection<Pictures> pictures = new LinkedList<Pictures>();
	
	public Books() {
		
	}
	
	public Books(String name) {
		super();
		this.name = name;
	}
	
	public Books(String name, String author, String language, String description, BigDecimal price) {
		super();
		this.name = name;
		this.author = author;
		this.language = language;
		this.description = description;
		this.price = price;
	}

	public Books(String name, String author, String language, String description, BigDecimal price,
			Collection<Pictures> pictures) {
		super();
		this.name = name;
		this.author = author;
		this.language = language;
		this.description = description;
		this.price = price;
		this.pictures = pictures;
	}

	public Books(Long id, String name, String author, String language, String description, BigDecimal price,
			Collection<Pictures> pictures) {
		this.id = id;
		this.name = name;
		this.author = author;
		this.language = language;
		this.description = description;
		this.price = price;
		this.pictures = pictures;
	}

	@Override
	public String toString() {
		return "Books [id=" + id + ", name=" + name + ", author=" + author + ", language=" + language + ", description="
				+ description + ", price=" + price + ", pictures=" + pictures + "]";
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

	public Collection<Pictures> getPictures() {
		return pictures;
	}

	public void setPictures(Collection<Pictures> pictures) {
		this.pictures = pictures;
	}
}
