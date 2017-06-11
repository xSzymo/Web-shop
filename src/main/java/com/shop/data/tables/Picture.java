package com.shop.data.tables;

import javax.persistence.*;

@Entity
@Table(name = "product_pictures")
public class Picture {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;
    @Column(name = "path", nullable = true)
    private String path;
    @Column(name = "file_type")
    private String fileType;

    public Picture() {

    }

    public Picture(String path, String name) {
        this.name = name;
        this.path = "." + path;
        if (fileType == null)
            fileType = "";
    }

    public Picture(String path, String name, String fileType) {
        this.name = name;
        this.path = path;
        this.fileType = "." + fileType;
        if (fileType == null)
            this.fileType = "";
    }

    @Override
    public String toString() {
        return "Pictures [id=" + id + ", Name=" + name + ", Path=" + path + ", fileType=" + fileType + "]";
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

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = "." + fileType;
        if (fileType == null)
            this.fileType = "";
    }

}
