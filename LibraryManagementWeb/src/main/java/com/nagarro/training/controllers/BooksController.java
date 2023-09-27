package com.nagarro.training.controllers;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.nagarro.training.models.Book;
import com.nagarro.training.services.BookService;

@Controller
public class BooksController {
	@Autowired
	BookService bookService;

	@GetMapping("/")
	public ModelAndView getBooks() {
		ModelAndView mv = new ModelAndView("index");

		mv.addObject("books", bookService.fetchAllBooks());

		return mv;
	}

	@GetMapping("/books")
	@ResponseBody
	public Book fetchBookById(@RequestParam int bid) {
		return bookService.fetchBookByID(bid);
	}

	@GetMapping("/books/book")
	public ModelAndView modifyBookView(@RequestParam(required = false) String bid) {
		ModelAndView mv = new ModelAndView("modifyBook");
		if (bid == null) {
			mv.addObject("title", "Add Book");
			mv.addObject("formHead", "Add Book Details");
			mv.addObject("addedOn", new SimpleDateFormat("dd MMMM yyyy").format(new Date()));
		} else {
			Book book = bookService.fetchBookByID(Integer.parseInt(bid));
			mv.addObject("title", "Edit Book");
			mv.addObject("formHead", "Edit Book Details");
			mv.addObject("book", book);
		}
		mv.addObject("authors", bookService.fetchAllAuthors());
		return mv;
	}

	@PostMapping("/books/book")
	@ResponseBody
	public String addBook(@RequestBody Book book) {
		return bookService.addBook(book);
	}

	@PutMapping("/books/book")
	@ResponseBody
	public String updateBook(@RequestBody Book book) {
		return bookService.updateBook(book);
	}

	@DeleteMapping("/books/book")
	@ResponseBody
	public String deleteBook(@RequestParam int bid) {
		return bookService.deleteBook(bid);
	}
}
