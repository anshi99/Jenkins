package com.nagarro.training.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientException;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nagarro.training.constants.Constants;
import com.nagarro.training.models.Author;
import com.nagarro.training.models.Book;

@Component
public class BookService {
	@Autowired
	WebClient webClient;

	ObjectMapper objMapper = new ObjectMapper();

	public Book[] fetchAllBooks() {
		Book[] books = null;

		try {
			books = webClient.get().uri("/books").retrieve().bodyToMono(Book[].class).block();
		} catch (WebClientResponseException wcre) {
			System.out.println(Constants.RETRIEVING_RESPONSE_ERROR);
		} catch (WebClientException wce) {
			System.out.println(Constants.RESPONSE_BODY_PARSING_ERROR);
		} catch (Exception e) {
			System.out.println(Constants.UNKNOWN_ERROR);
		}

		return books;
	}

	public Book fetchBookByID(int bid) {
		Book book = null;

		try {
			book = webClient.get().uri("/books?bid=" + bid).retrieve().bodyToMono(Book.class).block();
		} catch (WebClientResponseException wcre) {
			System.out.println(Constants.RETRIEVING_RESPONSE_ERROR);
		} catch (WebClientException wce) {
			System.out.println(Constants.RESPONSE_BODY_PARSING_ERROR);
		} catch (Exception e) {
			System.out.println(Constants.UNKNOWN_ERROR);
		}

		System.out.println(book);

		return book;
	}

	public Author[] fetchAllAuthors() {
		Author[] authors = null;

		try {
			authors = webClient.get().uri("/authors").retrieve().bodyToMono(Author[].class).block();
		} catch (WebClientResponseException wcre) {
			System.out.println(Constants.RETRIEVING_RESPONSE_ERROR);
		} catch (WebClientException wce) {
			System.out.println(Constants.RESPONSE_BODY_PARSING_ERROR);
		} catch (Exception e) {
			System.out.println(Constants.UNKNOWN_ERROR);
		}

		return authors;
	}

	public String addBook(Book book) {
		System.out.println(book);
		String response = webClient.post().uri("/books/book").accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON).body(BodyInserters.fromValue(book)).retrieve()
				.bodyToMono(String.class).block();
		System.out.println(response);

		return response;
	}

	public String updateBook(Book book) {
		String response = webClient.put().uri("/books/book?bid=" + book.getBid()).accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON).body(BodyInserters.fromValue(book)).retrieve()
				.bodyToMono(String.class).block();
		System.out.println(response);

		return response;
	}

	public String deleteBook(int bid) {
		String response = webClient.delete().uri("/books/book?bid=" + bid).accept(MediaType.APPLICATION_JSON).retrieve()
				.bodyToMono(String.class).block();

		System.out.println(response);

		return response;
	}
}
