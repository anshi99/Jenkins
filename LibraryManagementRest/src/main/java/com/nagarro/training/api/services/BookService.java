package com.nagarro.training.api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nagarro.training.api.dao.AuthorDao;
import com.nagarro.training.api.dao.BookDao;
import com.nagarro.training.api.models.Book;
import com.nagarro.training.constants.Constants;

@Component
public class BookService {
	ObjectMapper objMapper = new ObjectMapper();

	@Autowired
	BookDao bookDao;
	@Autowired
	Book book;
	@Autowired
	AuthorDao authorDao;

	// Method to list all books from database
	public String listBooks() {
		String resp = null;

		try {
			resp = objMapper.writeValueAsString(bookDao.findAll());
		} catch (JsonProcessingException | NumberFormatException e) {
			// TODO Auto-generated catch block
			resp = Constants.JSON_ERROR_RESPONSE_STRING + Constants.JSON_PROCESSING_ERROR + "\"}";
			e.printStackTrace();
		} catch (Exception e) {
			resp = Constants.JSON_ERROR_RESPONSE_STRING + Constants.UNKNOWN_ERROR + "\"}";
		}

		return resp;
	}

	// Method to get Book By ID
	public String getBookById(int bid) {
		String resp = null;

		try {
			resp = objMapper.writeValueAsString(bookDao.findById(bid).orElse(null));
		} catch (JsonProcessingException | NumberFormatException e) {
			e.printStackTrace();
		} catch (Exception e) {
			resp = Constants.JSON_ERROR_RESPONSE_STRING + Constants.UNKNOWN_ERROR + "\"}";
		}

		return resp;
	}

	// Method to add book in database
	public String addBook(Book newBook) {
		String resp = null;
		try {
			Book checkBook = bookDao.findById(newBook.getBid()).orElse(null);
			if (checkBook == null) {
				book.setBid(newBook.getBid());
				book.setBname(newBook.getBname());
				book.setAuthor(newBook.getAuthor());
				book.setAddedOn(newBook.getAddedOn());

				if (book.getAuthor() != null)
					resp = objMapper.writeValueAsString(bookDao.save(book));
				else
					resp = Constants.JSON_ERROR_RESPONSE_STRING + Constants.AUTHOR_NOT_AVAILABLE + "\"}";
			} else {
				resp = Constants.JSON_ERROR_RESPONSE_STRING + Constants.BOOK_ALREADY_EXISTS + "\"}";
			}

		} catch (NumberFormatException | ClassCastException | NullPointerException | JsonProcessingException e) {
			resp = Constants.JSON_ERROR_RESPONSE_STRING + Constants.JSON_PROCESSING_ERROR + "\"}";
		} catch (IllegalArgumentException iae) {
			resp = Constants.JSON_ERROR_RESPONSE_STRING + Constants.AUTHOR_NOT_AVAILABLE + "\"}";
		} catch (Exception e) {
			resp = Constants.JSON_ERROR_RESPONSE_STRING + Constants.UNKNOWN_ERROR + " \"}";
		}

		return resp;
	}

	// Method to update Book from database
	public String updateBook(Book newBook) {
		String resp = null;
		try {
			Book oldBook = bookDao.findById(newBook.getBid()).orElse(null);
			if (oldBook != null) {
				book.setBid(oldBook.getBid());
				book.setBname(newBook.getBname());
				book.setAuthor(newBook.getAuthor());
				book.setAddedOn(oldBook.getAddedOn());

				if (book.getAuthor() != null)
					resp = objMapper.writeValueAsString(bookDao.save(book));
				else
					resp = Constants.JSON_ERROR_RESPONSE_STRING + Constants.AUTHOR_NOT_AVAILABLE + "\"}";
			} else {
				resp = Constants.JSON_ERROR_RESPONSE_STRING + Constants.BOOK_NOT_FOUND + "\"}";
			}

		} catch (NumberFormatException | ClassCastException | NullPointerException | JsonProcessingException e) {
			resp = Constants.JSON_ERROR_RESPONSE_STRING + Constants.JSON_PROCESSING_ERROR + "\"}";
		} catch (IllegalArgumentException iae) {
			resp = Constants.JSON_ERROR_RESPONSE_STRING + Constants.AUTHOR_NOT_AVAILABLE + "\"}";
		} catch (Exception e) {
			resp = Constants.JSON_ERROR_RESPONSE_STRING + Constants.UNKNOWN_ERROR + "\"}";
		}

		return resp;
	}

	// Method to delete a book from database
	public String deleteBook(int bid) {
		String resp = null;
		try {
			Book book = bookDao.findById(bid).orElse(null);
			System.out.println(book);

			if (book != null) {
				bookDao.delete(book);
				resp = "{\"success\" : true}";
			} else {
				resp = Constants.JSON_ERROR_RESPONSE_STRING + Constants.BOOK_NOT_FOUND + "\"}";
			}

		} catch (NumberFormatException | ClassCastException | NullPointerException e) {
			resp = Constants.JSON_ERROR_RESPONSE_STRING + Constants.JSON_PROCESSING_ERROR + "\"}";
		} catch (IllegalArgumentException iae) {
			resp = Constants.JSON_ERROR_RESPONSE_STRING + Constants.AUTHOR_NOT_AVAILABLE + "\"}";
		} catch (Exception e) {
			resp = Constants.JSON_ERROR_RESPONSE_STRING + Constants.UNKNOWN_ERROR + "\"}";
		}

		return resp;
	}
}
