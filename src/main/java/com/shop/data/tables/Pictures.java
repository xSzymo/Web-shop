package com.shop.data.tables;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "product_pictures")
public class Pictures {
	@Id
	@GeneratedValue
	@Column(name = "picture_id")
	private Long id;

	@Column(name = "product_picture_name")
	private String Name;
	@Column(name = "product_picture_path")
	private String Path;
	@Column(name = "product_picture_file_type")
	private String fileType;

	public Pictures() {

	}

	public Pictures(String name, String path, String fileType) {
		super();
		Name = name;
		Path = path;
		this.fileType = fileType;
	}

	public Pictures(Long id, String name, String path, String fileType) {
		super();
		this.id = id;
		Name = name;
		Path = path;
		this.fileType = fileType;
	}

	@Override
	public String toString() {
		return "Pictures [id=" + id + ", Name=" + Name + ", Path=" + Path + ", fileType=" + fileType + "]";
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getPath() {
		return Path;
	}

	public void setPath(String path) {
		Path = path;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

}
