package com.example.book.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.book.model.BooksDetail;
import com.example.book.model.FileInfo;

public interface FileInfoRepository extends JpaRepository<FileInfo, Long> {

}
