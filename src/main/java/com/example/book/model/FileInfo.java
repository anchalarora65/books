package com.example.book.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "books_image")
public class FileInfo {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "file_name")
	private String name;

	@Column(name = "url")
	private String url;

	@Column(name = "book_id")
	private Long bookId;

	public FileInfo(String name, String url) {
		// this.id = id;
		this.name = name;
		this.url = url;
	}

	public FileInfo(Long id, String name, String url, Long bookId) {
		this.id = id;
		this.name = name;
		this.url = url;
		this.bookId = bookId;
	}

	public FileInfo() {

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

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Long getBookId() {
		return bookId;
	}

	public void setBookId(Long bookId) {
		this.bookId = bookId;
	}

}
