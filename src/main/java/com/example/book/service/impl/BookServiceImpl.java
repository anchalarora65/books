package com.example.book.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.book.model.BooksDetail;
import com.example.book.model.Category;
import com.example.book.model.FileInfo;
import com.example.book.repository.BooksDetailRepository;
import com.example.book.repository.CategoryRepository;
import com.example.book.repository.FileInfoRepository;
import com.example.book.service.BookService;

@Service
public class BookServiceImpl implements BookService {

	@Autowired
	private CategoryRepository CategoryRepository;

	@Autowired
	private BooksDetailRepository booksDetailRepository;

	@Autowired
	private FileInfoRepository fileInfoRepository;

	@Override
	public List<Category> findAll() {

		List<Category> response = new ArrayList<>();
		List<BooksDetail> booksDetailsList = new ArrayList<>();
		List<Category> categories = CategoryRepository.findAll();
		for (Category category2 : categories) {
			Category category = new Category();
			category.setCategoryName(category2.getCategoryName());
			category.setId(category2.getId());
			category.setInsertDate(category2.getInsertDate());
			category.setUpdateDate(category2.getUpdateDate());

			List<BooksDetail> book = booksDetailRepository.findByCategoryId(category2.getId());
			for (BooksDetail booksDetail : book) {
				BooksDetail booksDetail_ = new BooksDetail();
				booksDetail_.setAuthor(booksDetail.getAuthor());
				booksDetail_.setCategoryId(booksDetail.getCategoryId());
				booksDetail_.setDescription(booksDetail.getDescription());
				booksDetail_.setId(booksDetail.getId());
				booksDetail_.setInsertDate(booksDetail.getInsertDate());
				booksDetail_.setName(booksDetail.getName());
				booksDetail_.setPrice(booksDetail.getPrice());
				booksDetail_.setUpdateDate(booksDetail.getUpdateDate());
				List<FileInfo> files = fileInfoRepository.findByBookId(booksDetail.getId());
				booksDetail_.setFiles(files);
				booksDetailsList.add(booksDetail_);

			}
			category.setBooks(booksDetailsList);
			response.add(category);
		}

		return response;
	}

	@Override
	public BooksDetail getBookDetailsById(Long bookId) {

		BooksDetail book = new BooksDetail();

		Optional<BooksDetail> booksDetail = booksDetailRepository.findById(bookId);
		if (booksDetail != null) {
			BooksDetail book_ = booksDetail.get();
			book.setId(book_.getId());
			book.setAuthor(book_.getAuthor());
			book.setDescription(book_.getAuthor());
			book.setName(book_.getName());
			book.setPrice(book_.getPrice());
			book.setCategoryId(book_.getCategoryId());
			book.setInsertDate(book_.getInsertDate());
			book.setUpdateDate(book_.getUpdateDate());
			List<FileInfo> fileInfos = fileInfoRepository.findByBookId(bookId);

			book.setFiles(fileInfos);
		}

		return book;
	}

	@Override
	public Category getCategoryDetailsById(Long categoryId) {

		Category category = new Category();
		List<BooksDetail> booksDetailsList = new ArrayList<>();
		Optional<Category> category_ = CategoryRepository.findById(categoryId);
		if (category_.isPresent()) {

			Category category2 = category_.get();

			category.setCategoryName(category2.getCategoryName());
			category.setId(category2.getId());
			category.setInsertDate(category2.getInsertDate());
			category.setUpdateDate(category2.getUpdateDate());

			List<BooksDetail> book = booksDetailRepository.findByCategoryId(categoryId);
			for (BooksDetail booksDetail : book) {
				BooksDetail booksDetail_ = new BooksDetail();
				booksDetail_.setAuthor(booksDetail.getAuthor());
				booksDetail_.setCategoryId(booksDetail.getCategoryId());
				booksDetail_.setDescription(booksDetail.getDescription());
				booksDetail_.setId(booksDetail.getId());
				booksDetail_.setInsertDate(booksDetail.getInsertDate());
				booksDetail_.setName(booksDetail.getName());
				booksDetail_.setPrice(booksDetail.getPrice());
				booksDetail_.setUpdateDate(booksDetail.getUpdateDate());
				List<FileInfo> files = fileInfoRepository.findByBookId(booksDetail.getId());
				booksDetail_.setFiles(files);
				booksDetailsList.add(booksDetail_);

			}
			category.setBooks(booksDetailsList);

		}

		return category;
	}

}
