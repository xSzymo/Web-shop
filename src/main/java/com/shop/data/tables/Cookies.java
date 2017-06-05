package com.shop.data.tables;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "cookies")
public class Cookies {
    @Id
    @GeneratedValue
    @Column(name = "cookie_id")
    private Long id;

    @Column(name = "name")
    private String name;
    @Column(name = "value")
    private String value;
    @Column(name = "date")
    private Date date;

    Cookies() {

    }

    public Cookies(String name, String value) {
        this.value = value;
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
