package com.example.book.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.book.model.BooksDetail;

public interface BooksDetailRepository extends JpaRepository<BooksDetail, Long> {

}
