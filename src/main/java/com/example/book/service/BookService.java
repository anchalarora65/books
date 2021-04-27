package com.example.book.service;

import java.util.List;

import com.example.book.model.BooksDetail;
import com.example.book.model.Category;

public interface BookService {

	List<Category> findAll();

	BooksDetail getBookDetailsById(Long bookId);

	Category getCategoryDetailsById(Long categoryId);
}
