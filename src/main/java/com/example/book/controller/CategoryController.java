package com.example.book.controller;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.book.model.Category;
import com.example.book.repository.CategoryRepository;

@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class CategoryController {

	@Autowired
	private CategoryRepository categoryRepository;

	@RequestMapping(value = "/category", method = RequestMethod.POST)
	public ResponseEntity<?> createCategory(@RequestBody Category category) {
		try {
			category.setInsertDate(new Date());
			category.setUpdateDate(new Date());
			Category category_ = categoryRepository.save(category);
			return new ResponseEntity<>(category_, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@RequestMapping(value = "/category", method = RequestMethod.GET)
	public ResponseEntity<?> getAllCategory() {
		try {
			List<Category> category = categoryRepository.findAll();
			if (category.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			} else {
				return new ResponseEntity<>(HttpStatus.OK);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@RequestMapping(value = "/category/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getCategoryById(@PathVariable("id") Long id) {
		try {
			Optional<Category> category = categoryRepository.findById(id);
			if (category.isPresent()) {
				return new ResponseEntity<>(category.get(), HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@RequestMapping(value = "/category/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteCategoryById(@PathVariable("id") Long id) {
		try {
			Optional<Category> category = categoryRepository.findById(id);
			if (category.isPresent()) {
				categoryRepository.deleteById(id);

				return new ResponseEntity<>("Deleted Successfully", HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@RequestMapping(value = "/category", method = RequestMethod.DELETE)
	public ResponseEntity<?> updateCategoryById(@RequestBody Category category) {
		try {
			Optional<Category> category_ = categoryRepository.findById(category.getId());
			if (category_.isPresent()) {

				return new ResponseEntity<>(categoryRepository.save(category), HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

}
