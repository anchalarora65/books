package com.example.book.response;

import com.example.book.model.BooksDetail;

public class ResponseMessage {
	private String message;
	private BooksDetail book;

	public ResponseMessage() {

	}

	public ResponseMessage(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public BooksDetail getBook() {
		return book;
	}

	public void setBook(BooksDetail book) {
		this.book = book;
	}

}
