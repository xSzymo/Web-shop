package com.shop.data.tables;

import javax.persistence.*;
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
    //@JoinColumn(name = "categories_id") // here sssss
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
        if(!this.getId().equals(category.getId()))
            sameObject = false;
        if(!this.getName().equals(category.getId()))
            sameObject = false;
        return sameObject;
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

    public synchronized Collection<Book> getBooks() {
        return books;
    }

    public void setBooks(Collection<Book> books) {
        this.books = books;
    }
}
