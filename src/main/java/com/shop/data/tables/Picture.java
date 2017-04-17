package com.shop.data.tables;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

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

	public Picture(String name, String path) {
		this.name = name;
		this.path = path;
	}

	public Picture(String name, String path, String fileType) {
		this.name = name;
		this.path = path;
		this.fileType = fileType;
	}

	public Picture(Long id, String name, String path, String fileType) {
		this.id = id;
		this.name = name;
		this.path = path;
		this.fileType = fileType;
	}

	@Override
	public String toString() {
		return "Pictures [id=" + id + ", Name=" + name + ", Path=" + path + ", fileType=" + fileType + "]";
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
		this.fileType = fileType;
	}

}
