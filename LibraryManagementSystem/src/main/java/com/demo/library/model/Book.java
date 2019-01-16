package com.demo.library.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Book {

	@Id
	private String _id;
	private String bookName;
	private String author;
	private Boolean deleted = false;

	/** constructors */

	public Book() {
	}

	public Book(String bookName, String author, Boolean deleted) {
		this.bookName = bookName;
		this.author = author;
		this.deleted = deleted;
	}

	/** getter&setters */

	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Boolean getDeleted() {
		return deleted;
	}

	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}

}
