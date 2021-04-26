package com.example.book.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import com.example.book.model.BooksDetail;
import com.example.book.model.FileInfo;
import com.example.book.repository.BooksDetailRepository;
import com.example.book.repository.FileInfoRepository;
import com.example.book.response.ResponseMessage;
import com.example.book.service.FilesStorageService;

@CrossOrigin("http://localhost:8080")
@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class BooksDetailController {

	@Autowired
	private BooksDetailRepository booksDetailRepository;

	@Autowired
	private FileInfoRepository fileInfoRepository;

	@Autowired
	FilesStorageService storageService;

	@RequestMapping(value = "/books", method = RequestMethod.POST)
	public ResponseEntity<?> createCategory(@RequestParam(name = "name") String name,
			@RequestParam(name = "description") String description, @RequestParam(name = "author") String author,
			@RequestParam(name = "price") Double price, @RequestParam(name = "categoryId") Long categoryId,
			@RequestBody MultipartFile[] files) {

		try {

			ResponseMessage msg = new ResponseMessage();

			BooksDetail book = new BooksDetail();
			book.setName(name);
			book.setDescription(description);
			book.setPrice(price);
			book.setAuthor(author);
			book.setCategoryId(categoryId);
			book.setInsertDate(new Date());
			book.setUpdateDate(new Date());
			BooksDetail book_ = booksDetailRepository.save(book);
			List<FileInfo> fileNames = new ArrayList<>();

			Arrays.asList(files).stream().forEach(file -> {
				storageService.save(file);
				FileInfo info = new FileInfo();
				info.setName(file.getOriginalFilename());
				Resource file_ = storageService.load(file.getOriginalFilename());
				try {
					info.setUrl(file_.getURI().toString());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					File original = file_.getFile();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				info.setBookId(book_.getId());
				fileNames.add(info);
			});
			List<String> nameList = new ArrayList<>();
			fileInfoRepository.saveAll(fileNames);

			// book.setFile(fileNames);
			fileNames.forEach(fileName ->

			// System.out.println(fileName.getName())
			nameList.add(fileName.getName())

			);

			msg.setMessage("Uploaded the files successfully:" + nameList);
			msg.setBook(book_);

			return ResponseEntity.status(HttpStatus.OK).body(msg);

		} catch (Exception e) {

			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body("Fail to upload files!");

		}

	}

	@GetMapping("/files")
	public ResponseEntity<List<FileInfo>> getListFiles() {
		List<FileInfo> fileInfos = storageService.loadAll().map(path -> {
			// String id = p
			String filename = path.getFileName().toString();
			String url = MvcUriComponentsBuilder
					.fromMethodName(BooksDetailController.class, "getFile", path.getFileName().toString()).build()
					.toString();

			return new FileInfo(filename, url);
		}).collect(Collectors.toList());

		return ResponseEntity.status(HttpStatus.OK).body(fileInfos);
	}

	@GetMapping("/files/{filename:.+}")
	public ResponseEntity<Resource> getFile(@PathVariable String filename) {
		Resource file = storageService.load(filename);
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
				.body(file);
	}

}
