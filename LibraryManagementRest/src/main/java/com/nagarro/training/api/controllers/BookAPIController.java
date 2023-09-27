package com.nagarro.training.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nagarro.training.api.models.Book;
import com.nagarro.training.api.services.BookService;
import com.nagarro.training.constants.Constants;

@RestController
@RequestMapping(Constants.BOOK_API_ROOT)
public class BookAPIController {

	@Autowired
	BookService bookService;

	@GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
	public String fetchBooks(@RequestParam(required = false) String bid) {
		if (bid == null) {
			// if bid is not present return all books
			return bookService.listBooks();
		} else {
			// if bid is present return only one book
			return bookService.getBookById(Integer.parseInt(bid));
		}
	}

	@PostMapping(value = Constants.BOOK_API_URL, produces = MediaType.APPLICATION_JSON_VALUE)
	public String addBook(@RequestBody Book book) {
		return bookService.addBook(book);
	}

	@PutMapping(value = Constants.BOOK_API_URL, produces = MediaType.APPLICATION_JSON_VALUE)
	public String updateBook(@RequestBody Book book) {
		System.out.println(book);
		return bookService.updateBook(book);
	}

	@DeleteMapping(value = Constants.BOOK_API_URL, produces = MediaType.APPLICATION_JSON_VALUE)
	public String deleteBook(@RequestParam int bid) {
		return bookService.deleteBook(bid);
	}
}
