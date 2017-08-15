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
        if (fileType.equals(""))
            fileType = "";
        if (fileType == null)
            this.fileType = "";
    }

    public Picture(String path, String name, String fileType) {
        this.name = name;
        this.path = path;
        this.fileType = "." + fileType;
        if (fileType.equals(""))
            this.fileType = "";
        if (fileType == null)
            this.fileType = "";
    }

    public boolean equals(Picture picture) {
        boolean sameObjects = true;
        if (!this.id.equals(picture.getId()))
            sameObjects = false;
        if (!this.name.equals(picture.getName()))
            sameObjects = false;
        if (!this.path.equals(picture.getPath()))
            sameObjects = false;
        if (!this.fileType.equals(picture.getFileType()))
            sameObjects = false;
        return sameObjects;
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
        if (fileType.equals(""))
            this.fileType = "";
        if (fileType == null)
            this.fileType = "";
    }

    /*public boolean equals(Picture picture) {
        boolean sameObjects = true;
        try {
            if (this.id == null && picture.getId() != null)
                return false;
            if (this.id != null && picture.getId() == null)
                return false;
            if (this.id != null && picture.getId() != null)
                if (!this.id.equals(picture.getId()))
                    sameObjects = false;
        } catch(NullPointerException e) {
            if(this.id != null)
        }
        if (!this.name.equals(picture.getName()))
            sameObjects = false;
        if (!this.path.equals(picture.getPath()))
            sameObjects = false;
        if (!this.fileType.equals(picture.getFileType()))
            sameObjects = false;
        return sameObjects;
    }*/

}
