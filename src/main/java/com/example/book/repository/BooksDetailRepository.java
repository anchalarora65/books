package com.example.book.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.book.model.BooksDetail;

public interface BooksDetailRepository extends JpaRepository<BooksDetail, Long> {
	List<BooksDetail> findByCategoryId(Long categoryId);
}
